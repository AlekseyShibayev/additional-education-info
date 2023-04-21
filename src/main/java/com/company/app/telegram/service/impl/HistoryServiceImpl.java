package com.company.app.telegram.service.impl;

import com.company.app.telegram.entity.Chat;
import com.company.app.telegram.entity.History;
import com.company.app.telegram.repository.HistoryRepository;
import com.company.app.telegram.service.api.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HistoryServiceImpl implements HistoryService {

	@Autowired
	HistoryRepository historyRepository;

	@Override
	public void save(String text) {
		historyRepository.save(History.builder()
				.message(text)
				.date(new Date())
				.build());
	}

	@Override
	public void save(Chat chat, String text) {
		historyRepository.save(History.builder()
				.chat(chat)
				.message(text)
				.date(new Date())
				.build());
	}

	@Override
	public void save(Long chatId, String text) {
		historyRepository.save(History.builder()
				.chat(Chat.builder().chatId(chatId).build())
				.message(text)
				.date(new Date())
				.build());
	}
}
