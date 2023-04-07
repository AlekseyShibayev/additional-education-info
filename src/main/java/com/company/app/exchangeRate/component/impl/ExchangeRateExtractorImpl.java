package com.company.app.exchangeRate.component.impl;

import com.company.app.core.tool.api.DataExtractorTool;
import com.company.app.exchangeRate.component.api.ExchangeRateExtractor;
import com.company.app.exchangeRate.entity.ExchangeRate;
import com.company.app.exchangeRate.repository.ExchangeRepository;
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

@Component
@Setter
public class ExchangeRateExtractorImpl implements ExchangeRateExtractor {

	@Value("${exchangeRate.aliexpressUrl}")
	private String aliexpressUrl;

	@Autowired
	private DataExtractorTool dataExtractorService;
	@Autowired
	private ExchangeRepository exchangeRepository;

	@SneakyThrows
	@Override
	public ExchangeRate extract() {
		ExchangeRate exchange = ExchangeRate.builder()
				.aliexpressExchangeRate(getExchangeRate(dataExtractorService.getHtmlResponse(aliexpressUrl)))
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
				String s = script.childNodes().get(0).attributes().get("#data");
				JSONObject jsonObject = new JSONObject(s);
				activityAmount = dataExtractorService.getJsonObject(jsonObject, "activityAmount");
				if (activityAmount != null) {
					break;
				}
			} catch (Exception e) {
				continue;
			}
		}

		if (activityAmount == null) {
			throw new RuntimeException("не смог определить курс  али");
		} else {
			return activityAmount;
		}
	}
}
