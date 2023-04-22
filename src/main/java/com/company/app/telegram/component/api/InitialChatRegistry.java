package com.company.app.telegram.component.api;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface InitialChatRegistry {

	void init() throws TelegramApiException;
}
