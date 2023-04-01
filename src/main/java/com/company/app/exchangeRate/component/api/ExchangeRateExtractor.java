package com.company.app.exchangeRate.component.api;

import com.company.app.exchangeRate.entity.ExchangeRate;
import org.springframework.stereotype.Component;

@Component
public interface ExchangeRateExtractor {

	ExchangeRate extract();
}
