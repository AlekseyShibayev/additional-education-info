package com.company.app.telegram.component.impl;

import com.company.app.core.tools.api.DataExtractorService;
import com.company.app.telegram.component.api.ChatRegistry;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Getter
@Component
public class CharRegistryImpl implements ChatRegistry {

	private static final String CHAT_PROPERTIES = "telegram/chat.properties";

	private Map<Long, String> chats;

	@Autowired
	private DataExtractorService dataExtractorService;

	// todo создай нормально мапу с чатами
	@PostConstruct
	public void init() throws TelegramApiException {
		Map<Long, String> result = new HashMap<>();
		dataExtractorService.getProperties(CHAT_PROPERTIES)
				.forEach((key, value) -> result.put(Long.parseLong(key), value));
		this.chats = result;
	}
}
