package com.company.app.telegram.component.impl;

import com.company.app.telegram.component.api.BinderExecutor;
import com.company.app.telegram.component.api.TelegramBotConfig;
import com.company.app.telegram.component.api.TelegramService;
import com.company.app.telegram.entity.Chat;
import com.company.app.telegram.service.api.ChatService;
import com.company.app.telegram.service.api.HistoryService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
public class TelegramServiceImpl implements TelegramService {

	@Autowired
	HistoryService historyService;
	@Autowired
	TelegramBotConfig telegramBotConfig;
	@Autowired
	BinderExecutor binderExecutor;
	@Autowired
	ChatService chatService;

	@Override
	public void read(Update update) {
		Message message = update.getMessage();
		Long chatId = message.getChatId();
		String text = message.getText();

		Chat chat = chatService.saveIfNotExistAndGet(chatId);
		log.debug("Читаю из чата [{}] сообщение [{}].", chatId, text);
		historyService.save(chat, text);

		binderExecutor.execute(chat, text);
	}

	@SneakyThrows
	@Override
	public void write(Object message) {
		log.debug("Пишу в телеграм: [{}].", message);

		chatService.getAll().stream()
				.filter(Chat::isEnableNotifications)
				.peek(chat -> historyService.save(chat, String.valueOf(message)))
				.map(chat -> SendMessage.builder().text(message.toString()).chatId(chat.getChatId().toString()).build())
				.forEach(telegramBotConfig::write);
	}
}
