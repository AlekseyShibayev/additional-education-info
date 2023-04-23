package com.company.app.springboot.application.exchangerate;

import com.company.app.exchangerate.component.api.ExchangeRateBinder;
import com.company.app.exchangerate.controller.ExchangeRateController;
import com.company.app.exchangerate.entity.ExchangeRate;
import com.company.app.exchangerate.repository.ExchangeRepository;
import com.company.app.springboot.application.ApplicationSpringBootTestContext;
import com.company.app.telegram.component.data.BinderContainer;
import com.company.app.telegram.entity.Chat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

class ExchangeRateEndToEndTest extends ApplicationSpringBootTestContext {

	@Autowired
	ExchangeRateController exchangeRateController;
	@Autowired
	ExchangeRateBinder exchangeRateBinder;
	@Autowired
	ExchangeRepository exchangeRepository;

	@BeforeEach
	void init() {
		exchangeRepository.deleteAll();
	}

	@Test
	void controller_test() {
		ExchangeRate exchangeRate = exchangeRateController.get().getBody();
		Assertions.assertNotNull(exchangeRate);
		Assertions.assertNotNull(exchangeRate.getCreationDate());
		Assertions.assertNotNull(exchangeRate.getAliexpressExchangeRate());
	}

	@Test
	void binder_fail_test() {
		BinderContainer binderContainer = createBinderContainer();
		Assertions.assertThrows(NoSuchElementException.class, () -> exchangeRateBinder.bind(binderContainer));
	}

	@Test
	void binder_success_test() {
		ExchangeRate exchangeRate = exchangeRateController.get().getBody();
		BinderContainer binderContainer = createBinderContainer();
		Assertions.assertDoesNotThrow(() -> exchangeRateBinder.bind(binderContainer));
	}

	private static BinderContainer createBinderContainer() {
		return BinderContainer.builder()
				.chat(Chat.builder().chatId(1L).build())
				.message("EX")
				.build();
	}
}
