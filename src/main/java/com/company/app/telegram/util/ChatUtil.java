package com.company.app.telegram.util;

import com.company.app.telegram.dto.ChatDto;
import com.company.app.telegram.entity.Chat;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

@UtilityClass
public class ChatUtil {

	public static ChatDto of(Chat chat) {
		ChatDto chatDto = new ChatDto();
		BeanUtils.copyProperties(chat, chatDto);
		return chatDto;
	}

	public static Chat of(ChatDto chatDto) {
		Chat chat = new Chat();
		BeanUtils.copyProperties(chatDto, chat);
		return chat;
	}

	public static Chat of(Long id, ChatDto chatDto) {
		Chat chat = new Chat();
		BeanUtils.copyProperties(chatDto, chat);
		chat.setId(id);
		return chat;
	}
}
