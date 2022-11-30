package com.company.app.service.telegram.api;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Map;

@Component
public interface TelegramFacade {

	Map<Long, String> getChats();

	void execute(SendMessage answer);
}
