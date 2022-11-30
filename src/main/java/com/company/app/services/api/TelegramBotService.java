package com.company.app.services.api;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Map;

public interface TelegramBotService {

	void sendAnswer(SendMessage answer);

	Map<Long, String> getChats();
}
