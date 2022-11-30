package com.company.app.repository;

import com.company.app.entity.Exchange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRepository extends CrudRepository<Exchange, Long> {

	List<Exchange> findAllByOrderByDateDesc();
}
