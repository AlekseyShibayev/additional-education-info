package com.company.app.exchangerate.repository;

import com.company.app.exchangerate.entity.ExchangeRate;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ExchangeRepository extends CrudRepository<ExchangeRate, Long> {

	Optional<ExchangeRate> findOneByOrderByCreationDateDesc();
}
