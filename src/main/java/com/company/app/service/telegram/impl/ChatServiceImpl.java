package com.company.app.service.telegram.impl;

import com.company.app.service.telegram.api.ChatService;
import com.company.app.service.tools.api.DataExtractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ChatServiceImpl implements ChatService {

	private static final String CHAT_PROPERTIES = "chat.properties";

	private Map<Long, String> chats;

	@Autowired
	private DataExtractorService dataExtractorService;

	@PostConstruct
	public void init() throws TelegramApiException {
		Map<Long, String> result = new HashMap<>();
		dataExtractorService.getProperties(CHAT_PROPERTIES)
				.forEach((key, value) -> result.put(Long.parseLong(key), value));
		this.chats = result;
	}

	public Map<Long, String> getChats() {
		return chats;
	}
}
