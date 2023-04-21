package com.company.app.telegram.service.api;

import com.company.app.telegram.entity.Subscription;

import java.util.Set;

public interface SubscriptionService {

	Subscription read(Long id);

	Set<Subscription> getAll();
}
