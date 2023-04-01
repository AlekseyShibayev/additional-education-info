package com.company.app.exchangeRate;

import com.company.app.core.aop.logging.PerformanceLogAnnotation;
import com.company.app.exchangeRate.component.api.ExchangeRateExtractor;
import com.company.app.exchangeRate.entity.ExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateFacade {

	@Autowired
	private ExchangeRateExtractor exchangeExtractorService;

	@PerformanceLogAnnotation
	public ExchangeRate extract() {
		return exchangeExtractorService.extract();
	}
}
