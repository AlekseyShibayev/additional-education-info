package com.company.app.service.telegram.components.impl;

import com.company.app.service.telegram.components.api.TelegramBotService;
import com.company.app.service.telegram.components.api.TelegramHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;

@Component
public class TelegramBotServiceImpl extends TelegramLongPollingCommandBot implements TelegramBotService {

	@Value("${telegram.name}")
	private String name;
	@Value("${telegram.token}")
	private String token;

	@Autowired
	private TelegramHandler telegramHandler;

	@PostConstruct
	public void init() throws TelegramApiException {
		TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
		botsApi.registerBot(this);
	}

	@Override
	public String getBotUsername() {
		return name;
	}

	@Override
	public String getBotToken() {
		return token;
	}

	@Override
	public void processNonCommandUpdate(Update update) {
		telegramHandler.process(update);
	}
}
