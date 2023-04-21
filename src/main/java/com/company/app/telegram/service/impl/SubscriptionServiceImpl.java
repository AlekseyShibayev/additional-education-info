package com.company.app.telegram.service.impl;

import com.company.app.telegram.entity.Subscription;
import com.company.app.telegram.repository.SubscriptionRepository;
import com.company.app.telegram.service.api.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	SubscriptionRepository subscriptionRepository;

	@Override
	public Subscription read(Long id) {
		return subscriptionRepository.findById(id).get();
	}

	@Override
	public Set<Subscription> getAll() {
		return subscriptionRepository.findAll();
	}
}
