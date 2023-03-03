package com.company.app.service.exchangeRate;

import com.company.app.entity.ExchangeRate;
import com.company.app.service.exchangeRate.components.api.ExchangeRateExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateFacade {

	@Autowired
	private ExchangeRateExtractor exchangeExtractorService;

	public ExchangeRate extract() {
		return exchangeExtractorService.extract();
	}
}
