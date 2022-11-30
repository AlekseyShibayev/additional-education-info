package com.company.app.services.telegram.impl;

import com.company.app.services.telegram.api.ChatService;
import com.company.app.services.telegram.api.TelegramBotService;
import com.company.app.services.telegram.api.TelegramHandler;
import com.company.app.tools.api.DataExtractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class TelegramBotServiceImpl extends TelegramLongPollingCommandBot implements TelegramBotService {

	private static final String TELEGRAM_PROPERTIES = "telegram.properties";
	private static final String NAME = "name";
	private static final String TOKEN = "token";

	private String name;
	private String token;

	@Autowired
	private DataExtractorService dataExtractorService;
	@Autowired
	private ChatService chatService;
	@Autowired
	private TelegramHandler telegramHandler;

	@PostConstruct
	public void init() throws TelegramApiException {
		Map<String, String> telegramProperties = dataExtractorService.getProperties(TELEGRAM_PROPERTIES);
		this.name = telegramProperties.get(NAME);
		this.token = telegramProperties.get(TOKEN);
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
