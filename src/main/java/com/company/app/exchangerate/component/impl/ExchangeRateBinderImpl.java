package com.company.app.exchangerate.component.impl;

import com.company.app.exchangerate.component.api.ExchangeRateBinder;
import com.company.app.exchangerate.entity.ExchangeRate;
import com.company.app.exchangerate.service.ExchangeRateService;
import com.company.app.telegram.component.TelegramFacade;
import com.company.app.telegram.component.data.BinderContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateBinderImpl implements ExchangeRateBinder {

	private static final String TYPE = "EX";

	@Autowired
	ExchangeRateService exchangeRateService;
	@Autowired
	TelegramFacade telegramFacade;

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public void bind(BinderContainer binderContainer) {
		ExchangeRate last = exchangeRateService.getLast();
		telegramFacade.write(last);
	}
}
