package com.company.app.telegram.repository;

import com.company.app.telegram.entity.Subscription;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {

	List<Subscription> findAll();
}
