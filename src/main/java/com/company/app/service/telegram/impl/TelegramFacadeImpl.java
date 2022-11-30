package com.company.app.service.telegram.impl;

import com.company.app.service.telegram.api.ChatService;
import com.company.app.service.telegram.api.TelegramFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Map;

@Component
public class TelegramFacadeImpl implements TelegramFacade {

	@Autowired
	private ChatService chatService;
	@Autowired
	private TelegramHandlerImpl telegramHandler;

	@Override
	public Map<Long, String> getChats() {
		return chatService.getChats();
	}

	@Override
	public void execute(SendMessage answer) {
		telegramHandler.execute(answer);
	}
}
