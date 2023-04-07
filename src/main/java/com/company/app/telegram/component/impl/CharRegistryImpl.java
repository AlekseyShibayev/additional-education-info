package com.company.app.telegram.component.impl;

import com.company.app.core.tools.api.DataExtractorService;
import com.company.app.telegram.component.api.ChatRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class CharRegistryImpl implements ChatRegistry {

	private static final String CHAT_PROPERTIES = "telegram/chat.properties";

	private Map<String, String> chats;

	@Autowired
	private DataExtractorService dataExtractorService;

	@PostConstruct
	public void init() throws TelegramApiException {
		this.chats = dataExtractorService.getProperties(CHAT_PROPERTIES);
	}

	@Override
	public Map<String, String> getAll() {
		return chats;
	}
}
