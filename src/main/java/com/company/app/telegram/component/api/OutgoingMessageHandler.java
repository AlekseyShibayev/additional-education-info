package com.company.app.telegram.component.api;

/**
 * Обрабатывает сообщения, отправленные телеграм ботом.
 */
public interface OutgoingMessageHandler {

	void work(Object message);
}
