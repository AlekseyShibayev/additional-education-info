package com.company.app.telegram.component.impl;

import com.company.app.core.tool.api.DataExtractorTool;
import com.company.app.telegram.component.api.ChatRegistry;
import com.company.app.telegram.entity.Chat;
import com.company.app.telegram.entity.UserInfo;
import com.company.app.telegram.repository.ChatRepository;
import com.company.app.telegram.service.api.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ChatRegistryImpl implements ChatRegistry {

	private static final String CHAT_PROPERTIES = "telegram/chat.properties";

	@Autowired
	DataExtractorTool dataExtractorTool;
	@Autowired
	ChatRepository chatRepository;
	@Autowired
	ChatService chatService;

	@PostConstruct
	public void init() throws TelegramApiException {
		Map<String, String> chats = dataExtractorTool.getProperties(CHAT_PROPERTIES);

		List<Chat> list = chats.keySet().stream()
				.map(chatId -> Chat.builder()
						.chatId(Long.valueOf(chatId))
						.enableNotifications(true)
						.userInfo(UserInfo.builder().role(chats.get(chatId)).build())
						.build()
				)
				.collect(Collectors.toList());

		chatRepository.saveAll(list);
	}

	@Override
	public List<Chat> getAll() {
		return chatRepository.findAll();
	}

	@Override
	public Chat saveIfNotExistAndGet(Long chatId) {
		if (chatRepository.existsChatByChatId(chatId)) {
			return chatRepository.findFirstByChatId(chatId).get();
		} else {
			return save(chatId);
		}
	}

	private Chat save(Long chatId) {
		Chat chat = Chat.builder().chatId(chatId).build();
		return chatRepository.save(chat);
	}
}
