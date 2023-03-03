package com.company.app.service.telegram;

import com.company.app.service.telegram.components.api.ChatService;
import com.company.app.service.telegram.components.impl.TelegramHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Map;

@Component
public class TelegramFacade {

	@Autowired
	private ChatService chatService;
	@Autowired
	private TelegramHandlerImpl telegramHandler;

	public Map<Long, String> getChats() {
		return chatService.getChats();
	}

	public void execute(SendMessage answer) {
		telegramHandler.execute(answer);
	}
}
