package com.company.app.exchangerate.service;

import com.company.app.exchangerate.entity.ExchangeRate;
import com.company.app.exchangerate.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

	@Autowired
	ExchangeRepository exchangeRepository;

	@Transactional
	@Override
	public ExchangeRate getLast() {
		Optional<ExchangeRate> optional = exchangeRepository.findFirstByOrderByCreationDateDesc();
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new NoSuchElementException("Курса еще нет.");
		}
	}

	@Transactional
	@Override
	public boolean create(ExchangeRate exchangeRate) {
		exchangeRepository.save(exchangeRate);
		return true;
	}
}
