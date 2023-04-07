package com.company.app.telegram.component.api;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface TelegramBotConfig {

	void write(SendMessage sendMessage);
}
