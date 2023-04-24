package com.company.app.telegram.component.impl;

import com.company.app.telegram.component.api.Binder;
import com.company.app.telegram.component.data.BinderContainer;
import com.company.app.telegram.entity.Chat;
import com.company.app.telegram.service.api.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelegramBinder implements Binder {

	private static final String TYPE = "TG";

	@Autowired
	ChatService chatService;

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public void bind(BinderContainer binderContainer) {
		Chat chat = binderContainer.getChat();
		chat.setEnableNotifications(false);
		chatService.update(chat);
	}

//	@Override
//	public void bind(BinderContainer binderContainer) {
//		Chat chat = binderContainer.getChat();
//		String message = binderContainer.getMessage().substring(3);
//
//		if (message.equals("+")) {
//			chat.setEnableNotifications(true);
//			Set<Subscription> subscriptions = subscriptionController.readAll().getBody();
//			chat.setSubscriptions(subscriptions);
//		} else if (message.equals("-")) {
//			chat.setEnableNotifications(false);
//		}
//
//		ChatDto chatDto = ChatUtil.of(chat);
//		chatController.update(chat.getId(), chatDto);
//	}
}
