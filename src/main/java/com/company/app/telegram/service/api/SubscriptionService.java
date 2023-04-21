package com.company.app.telegram.service.api;

import com.company.app.telegram.entity.Subscription;

import java.util.List;

public interface SubscriptionService {

	Subscription read(Long id);

	List<Subscription> getAll();
}
