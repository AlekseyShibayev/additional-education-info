package com.company.app.exchangerate;

import com.company.app.springboot.application.SpringBootApplicationContext;
import com.company.app.exchangerate.entity.ExchangeRate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ExchangeRateFacadeTest extends SpringBootApplicationContext {

	@Autowired
	private ExchangeRateFacade exchangeRateFacade;

	@Test
	void extractCurse() {
		ExchangeRate exchangeRate = exchangeRateFacade.extract();
		Assertions.assertNotNull(exchangeRate.getAliexpressExchangeRate());
	}
}