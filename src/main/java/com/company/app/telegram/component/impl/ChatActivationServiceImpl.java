package com.company.app.telegram.component.impl;

import com.company.app.telegram.component.api.ChatActivationService;
import com.company.app.telegram.controller.SubscriptionController;
import com.company.app.telegram.entity.Chat;
import com.company.app.telegram.entity.Subscription;
import com.company.app.telegram.service.api.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ChatActivationServiceImpl implements ChatActivationService {

	@Autowired
	SubscriptionController subscriptionController;
	@Autowired
	ChatService chatService;

	@Override
	public void fullActivate(Chat chat) {
		chat.setEnableNotifications(true);

		Set<Subscription> subscriptions = subscriptionController.readAll().getBody();
		chat.setSubscriptions(subscriptions);

		chatService.update(chat);
	}
}
