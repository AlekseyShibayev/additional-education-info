package com.company.app.telegram.component.api;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public interface TelegramService {

	void read(Update update);

	void write(SendMessage answer);
}
