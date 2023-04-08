package com.company.app.springboottest.component.exchangerate;

import com.company.app.exchangerate.ExchangeRateFacade;
import com.company.app.springboottest.application.SpringBootApplicationTestContext;
import com.company.app.exchangerate.entity.ExchangeRate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ExchangeRateFacadeTest extends SpringBootApplicationTestContext {

	@Autowired
	private ExchangeRateFacade exchangeRateFacade;

	@Test
	void extractCurse() {
		ExchangeRate exchangeRate = exchangeRateFacade.extract();
		Assertions.assertNotNull(exchangeRate.getAliexpressExchangeRate());
	}
}