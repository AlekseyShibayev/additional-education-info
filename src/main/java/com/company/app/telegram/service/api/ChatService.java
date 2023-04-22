package com.company.app.telegram.service.api;

import com.company.app.telegram.dto.ChatDto;
import com.company.app.telegram.entity.Chat;

import java.util.List;

public interface ChatService {

	Long create(ChatDto chatDto);

	Chat read(Long id);

	Boolean update(Long id, ChatDto chatDto);

	Boolean delete(Long id);

	Chat saveIfNotExistAndGet(Long chatId);

	List<Chat> getAll();
}
