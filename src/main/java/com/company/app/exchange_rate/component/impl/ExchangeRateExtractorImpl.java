package com.company.app.exchange_rate.component.impl;

import com.company.app.core.tool.api.DataExtractorTool;
import com.company.app.exchange_rate.component.api.ExchangeRateExtractor;
import com.company.app.exchange_rate.entity.ExchangeRate;
import com.company.app.exchange_rate.repository.ExchangeRepository;
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
import java.util.Optional;

@Component
@Setter
public class ExchangeRateExtractorImpl implements ExchangeRateExtractor {

	@Value("${exchangeRate.aliexpressUrl}")
	private String aliexpressUrl;

	@Autowired
	private DataExtractorTool dataExtractorTool;
	@Autowired
	private ExchangeRepository exchangeRepository;

	@SneakyThrows
	@Override
	public ExchangeRate extract() {
		ExchangeRate exchange = ExchangeRate.builder()
				.aliexpressExchangeRate(getExchangeRate(dataExtractorTool.getHtmlResponse(aliexpressUrl)))
				.date(new Date())
				.build();
		exchangeRepository.save(exchange);

		return exchange;
	}

	String getExchangeRate(String htmlResponse) {
		Document document = Jsoup.parse(htmlResponse);
		Elements scripts = document.getElementsByTag("script");
		JSONObject activityAmount = getActivityAmount(scripts);
		return String.valueOf(activityAmount.getDouble("value"));
	}

	private JSONObject getActivityAmount(Elements scripts) {
		JSONObject activityAmount = null;
		for (Element script : scripts) {
			try {
				activityAmount = getActivityAmount_(script);
				if (activityAmount != null) {
					break;
				}
			} catch (Exception e) {
			}
		}
		return Optional.ofNullable(activityAmount).orElseThrow(() -> new RuntimeException("Не смог вытащить курс али."));
	}

	private JSONObject getActivityAmount_(Element script) {
		String s = script.childNodes().get(0).attributes().get("#data");
		JSONObject jsonObject = new JSONObject(s);
		return dataExtractorTool.getJsonObject(jsonObject, "activityAmount");
	}
}
