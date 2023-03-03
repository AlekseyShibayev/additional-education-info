package com.company.app.service.telegram.components.api;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public interface TelegramHandler {

	void process(Update update);

	void execute(SendMessage answer);
}
