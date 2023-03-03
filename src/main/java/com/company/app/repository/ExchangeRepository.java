package com.company.app.repository;

import com.company.app.entity.ExchangeRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRepository extends CrudRepository<ExchangeRate, Long> {

	List<ExchangeRate> findAllByOrderByDateDesc();
}
