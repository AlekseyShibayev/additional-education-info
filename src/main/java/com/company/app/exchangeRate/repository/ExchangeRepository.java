package com.company.app.exchangeRate.repository;

import com.company.app.exchangeRate.entity.ExchangeRate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExchangeRepository extends CrudRepository<ExchangeRate, Long> {

	List<ExchangeRate> findAllByOrderByDateDesc();
}
