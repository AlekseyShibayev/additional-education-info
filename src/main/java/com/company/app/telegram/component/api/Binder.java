package com.company.app.telegram.component.api;

/**
 * Связывает модуль и входящее сообщение из телеграмм.
 */
public interface Binder {

	String getType();

	void bind(String string);
}
