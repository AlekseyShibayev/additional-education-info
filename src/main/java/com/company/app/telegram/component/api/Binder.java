package com.company.app.telegram.component.api;

/**
 * Класс, связывающий модуль и входящее сообщение из телеграмм.
 */
public interface Binder {

	String getType();

	void bind(String string);
}
