package com.company.app.telegram.component.api;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Регистрирует чаты, при старте приложения.
 */
public interface InitialChatRegistry {

	void init() throws TelegramApiException;
}
