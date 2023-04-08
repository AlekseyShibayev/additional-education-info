package com.company.app.exchangerate.component;

import com.company.app.exchangerate.ExchangeRateTestConfiguration;
import com.company.app.exchangerate.entity.ExchangeRate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = ExchangeRateTestConfiguration.class)
class ExchangeRateFacadeTest {

	@Autowired
	private ExchangeRateFacade exchangeRateFacade;

	@Test
	void extractCurse() {
		log.debug("**********     This is component test: [{}].     **********", this.getClass());
		ExchangeRate exchangeRate = exchangeRateFacade.extract();
		Assertions.assertNotNull(exchangeRate.getAliexpressExchangeRate());
	}
}