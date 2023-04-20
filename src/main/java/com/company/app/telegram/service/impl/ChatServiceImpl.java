package com.company.app.telegram.service.impl;

import com.company.app.telegram.dto.ChatDto;
import com.company.app.telegram.entity.Chat;
import com.company.app.telegram.repository.ChatRepository;
import com.company.app.telegram.service.api.ChatService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	ChatRepository chatRepository;

	@Override
	public Long create(ChatDto chatDto) {
		Chat chat = Chat.builder().build();
		BeanUtils.copyProperties(chatDto, chat);
		return chatRepository.save(chat).getId();
	}

	@Override
	public Chat read(Long id) {
		Optional<Chat> optional = chatRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new ObjectNotFoundException(id, Chat.class.getName());
		}
	}

	@Override
	public Boolean update(Long id, ChatDto chatDto) {
		Chat chat = Chat.builder()
				.id(id)
				.build();
		BeanUtils.copyProperties(chatDto, chat);
		chatRepository.save(chat);
		return true;
	}

	@Override
	public Boolean delete(Long id) {
		chatRepository.deleteById(id);
		return true;
	}
}
