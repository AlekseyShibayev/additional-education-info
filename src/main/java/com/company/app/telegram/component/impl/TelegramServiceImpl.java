package com.company.app.telegram.component.impl;

import com.company.app.telegram.component.api.IncomingMessageHandler;
import com.company.app.telegram.component.api.OutgoingMessageHandler;
import com.company.app.telegram.component.api.TelegramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TelegramServiceImpl implements TelegramService {

	@Autowired
	IncomingMessageHandler incomingMessageHandler;
	@Autowired
	OutgoingMessageHandler outgoingMessageHandler;

	@Override
	public void read(Update update) {
		incomingMessageHandler.work(update);
	}

	@Override
	public void write(Object message) {
		outgoingMessageHandler.work(message);
	}
}
