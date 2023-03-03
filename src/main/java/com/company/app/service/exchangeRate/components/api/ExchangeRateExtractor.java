package com.company.app.service.exchangeRate.components.api;

import com.company.app.entity.ExchangeRate;
import org.springframework.stereotype.Component;

@Component
public interface ExchangeRateExtractor {

	ExchangeRate extract();
}
