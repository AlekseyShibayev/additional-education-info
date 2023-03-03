package com.company.app.service.exchangeRate.components.impl;

import com.company.app.entity.ExchangeRate;
import com.company.app.repository.ExchangeRepository;
import com.company.app.service.exchangeRate.components.api.ExchangeRateExtractor;
import com.company.app.service.application.tools.api.DataExtractorService;
import lombok.Setter;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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
	private DataExtractorService dataExtractorService;
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
		String s = scripts.get(3).childNodes().get(0).attributes().get("#data"); //todo сделать в общем виде
		JSONObject jsonObject = new JSONObject(s);

		JSONObject activityAmount = dataExtractorService.getJsonObject(jsonObject, "activityAmount");
		return String.valueOf(activityAmount.getDouble("value"));
	}
}
