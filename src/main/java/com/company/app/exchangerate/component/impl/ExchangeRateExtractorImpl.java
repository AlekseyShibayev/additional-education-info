package com.company.app.exchangerate.component.impl;

import com.company.app.core.tool.api.DataExtractorTool;
import com.company.app.exchangerate.component.api.ExchangeRateExtractor;
import com.company.app.exchangerate.entity.ExchangeRate;
import com.company.app.exchangerate.service.ExchangeRateService;
import lombok.Setter;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
@Setter
public class ExchangeRateExtractorImpl implements ExchangeRateExtractor {

	@Value("${exchangeRate.aliexpressUrl}")
	private String aliexpressUrl;

	@Autowired
	private DataExtractorTool dataExtractorTool;
	@Autowired
	private ExchangeRateService exchangeRateService;

	@SneakyThrows
	@Override
	public ExchangeRate extract() {
		ExchangeRate exchange = ExchangeRate.builder()
				.aliexpressExchangeRate(getExchangeRate(dataExtractorTool.getHtmlResponse(aliexpressUrl)))
				.creationDate(new Date())
				.build();
		exchangeRateService.create(exchange);

		return exchange;
	}

	String getExchangeRate(String htmlResponse) {
		Document document = Jsoup.parse(htmlResponse);
		Elements scripts = document.getElementsByTag("script");
		JSONObject activityAmount = getActivityAmount(scripts);
		return String.valueOf(activityAmount.getDouble("value"));
	}

	private JSONObject getActivityAmount(Elements scripts) {
		return scripts.stream()
				.map(this::getInner)
				.filter(Objects::nonNull)
				.findFirst()
				.orElseThrow();
	}

	private JSONObject getInner(Element script) {
		try {
			String s = script.childNodes().get(0).attributes().get("#data");
			JSONObject jsonObject = new JSONObject(s);
			return dataExtractorTool.getJsonObject(jsonObject, "activityAmount");
		} catch (Exception e) {
			return null;
		}
	}
}
