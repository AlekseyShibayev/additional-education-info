package com.company.app.telegram.component.impl;

import com.company.app.core.tool.api.JsonSerializationTool;
import com.company.app.telegram.component.api.InitialChatRegistry;
import com.company.app.telegram.entity.Chat;
import com.company.app.telegram.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class InitialChatRegistryImpl implements InitialChatRegistry {

	@Value("classpath:telegram/init.json")
	private Resource resource;

	@Autowired
	JsonSerializationTool<Chat> jsonSerializationTool;
	@Autowired
	ChatRepository chatRepository;

	@PostConstruct
	public void init() throws TelegramApiException {
		List<Chat> list = jsonSerializationTool.load(resource, Chat.class);
		chatRepository.saveAll(list);
	}
}
