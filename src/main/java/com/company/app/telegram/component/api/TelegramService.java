package com.company.app.telegram.component.api;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface TelegramService {

	void read(Update update);

	void write(Object message);
}
