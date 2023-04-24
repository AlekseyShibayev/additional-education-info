package com.company.app.telegram.component.impl;

import com.company.app.telegram.component.api.OutgoingMessageHandler;
import com.company.app.telegram.component.api.TelegramBotConfig;
import com.company.app.telegram.entity.Chat;
import com.company.app.telegram.service.api.ChatService;
import com.company.app.telegram.service.api.HistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Slf4j
@Component
public class OutgoingMessageHandlerImpl implements OutgoingMessageHandler {

	@Autowired
	HistoryService historyService;
	@Autowired
	TelegramBotConfig telegramBotConfig;
	@Autowired
	ChatService chatService;

	@Override
	public void work(Object message) {
		log.debug("Пишу в телеграм: [{}].", message);

		chatService.getAll().stream()
				.filter(Chat::isEnableNotifications)
				.peek(chat -> historyService.save(chat, String.valueOf(message)))
				.map(chat -> SendMessage.builder().text(message.toString()).chatId(chat.getChatId().toString()).build())
				.forEach(telegramBotConfig::write);
	}
}
