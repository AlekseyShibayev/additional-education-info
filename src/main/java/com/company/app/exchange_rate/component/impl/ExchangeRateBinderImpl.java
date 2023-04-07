package com.company.app.exchange_rate.component.impl;

import com.company.app.core.service.api.NotificationService;
import com.company.app.exchange_rate.component.api.ExchangeRateBinder;
import com.company.app.exchange_rate.entity.ExchangeRate;
import com.company.app.exchange_rate.repository.ExchangeRepository;
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
	NotificationService notificationService;

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public void bind(String string) {
		Optional<ExchangeRate> optional = exchangeRepository.findOneByOrderByDateDesc();
		if (optional.isPresent()) {
			notificationService.notify(optional.get());
		} else {
			throw new NoSuchElementException("Курса еще нет.");
		}
	}
}
