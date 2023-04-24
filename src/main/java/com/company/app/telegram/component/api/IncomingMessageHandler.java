package com.company.app.telegram.component.api;

import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Обрабатывает сообщения, отправленные в телеграм бота.
 */
public interface IncomingMessageHandler {

	void work(Update update);
}
