package com.company.app.repository;

import com.company.app.entity.Lot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotRepository extends CrudRepository<Lot, Long> {

	@Override
	List<Lot> findAll();
}
