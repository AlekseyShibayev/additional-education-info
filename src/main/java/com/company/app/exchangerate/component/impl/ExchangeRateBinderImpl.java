package com.company.app.exchangerate.component.impl;

import com.company.app.exchangerate.component.api.ExchangeRateBinder;
import com.company.app.exchangerate.entity.ExchangeRate;
import com.company.app.exchangerate.repository.ExchangeRepository;
import com.company.app.telegram.component.TelegramFacade;
import com.company.app.telegram.component.data.BinderContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class ExchangeRateBinderImpl implements ExchangeRateBinder {

	private static final String TYPE = "EX";

	@Autowired
	ExchangeRepository exchangeRepository;
	@Autowired
	TelegramFacade telegramFacade;

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public void bind(BinderContainer binderContainer) {
		Optional<ExchangeRate> optional = exchangeRepository.findOneByOrderByDateDesc();
		if (optional.isPresent()) {
			telegramFacade.write(optional.get());
		} else {
			throw new NoSuchElementException("Курса еще нет.");
		}
	}
}
