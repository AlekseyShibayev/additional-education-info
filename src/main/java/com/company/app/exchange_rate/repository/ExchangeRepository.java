package com.company.app.exchange_rate.repository;

import com.company.app.exchange_rate.entity.ExchangeRate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ExchangeRepository extends CrudRepository<ExchangeRate, Long> {

	List<ExchangeRate> findAllByOrderByDateDesc();

	Optional<ExchangeRate> findOneByOrderByDateDesc();
}
