package com.company.app.exchangeRate.component.impl;

import com.company.app.exchangeRate.component.api.ExchangeRateBinder;
import com.company.app.exchangeRate.entity.ExchangeRate;
import com.company.app.exchangeRate.repository.ExchangeRepository;
import com.company.app.telegram.component.api.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateBinderImpl implements ExchangeRateBinder {

	@Autowired
	ExchangeRepository exchangeRepository;
	@Autowired
	NotificationService notificationService;

	@Override
	public void bind(String string) {
		ExchangeRate exchange = exchangeRepository.findAllByOrderByDateDesc().get(0);
		notificationService.eventNotification(exchange);
	}
}
