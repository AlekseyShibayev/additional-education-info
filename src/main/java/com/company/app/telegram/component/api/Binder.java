package com.company.app.telegram.component.api;

import com.company.app.telegram.component.data.BinderContainer;

/**
 * Связывает модуль и входящее сообщение из телеграмм.
 */
public interface Binder {

	String getType();

	void bind(BinderContainer binderContainer);
}
