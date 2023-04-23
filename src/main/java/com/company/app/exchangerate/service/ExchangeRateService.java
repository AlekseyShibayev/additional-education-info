package com.company.app.exchangerate.service;

import com.company.app.exchangerate.entity.ExchangeRate;

public interface ExchangeRateService {

	ExchangeRate getLast();

	boolean create(ExchangeRate exchangeRate);
}
