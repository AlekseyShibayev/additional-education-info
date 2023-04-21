package com.company.app.telegram.controller;

import com.company.app.telegram.entity.Subscription;
import com.company.app.telegram.service.api.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/telegram/subscription")
public class SubscriptionController {

	@Autowired
	SubscriptionService subscriptionService;

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Subscription> read(@PathVariable Long id) {
		return ResponseEntity.ok(subscriptionService.read(id));
	}

	@GetMapping(value = "/all", produces = "application/json")
	public ResponseEntity<List<Subscription>> read() {
		return ResponseEntity.ok(subscriptionService.getAll());
	}
}
