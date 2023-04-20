package com.company.app.telegram.component.impl;

import com.company.app.core.tool.api.DataExtractorTool;
import com.company.app.telegram.component.api.ChatRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class ChatRegistryImpl implements ChatRegistry {

	private static final String CHAT_PROPERTIES = "telegram/chat.properties";

	private Map<String, String> chats;

	@Autowired
	private DataExtractorTool dataExtractorTool;

	@PostConstruct
	public void init() throws TelegramApiException {
		this.chats = dataExtractorTool.getProperties(CHAT_PROPERTIES);
	}

	@Override
	public Map<String, String> getAll() {
		return chats;
	}
}
