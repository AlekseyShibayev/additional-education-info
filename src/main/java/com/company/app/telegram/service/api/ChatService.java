package com.company.app.telegram.service.api;

import com.company.app.telegram.dto.ChatDto;
import com.company.app.telegram.entity.Chat;

import java.util.List;

public interface ChatService {

	Long create(ChatDto chatDto);

	Chat read(Long id);

	Boolean update(Long id, ChatDto chatDto);

	Boolean update(Chat chat);

	Boolean delete(Long id);

	Chat getChatOrCreateIfNotExist(Long chatId);

	List<Chat> getAll();
}
