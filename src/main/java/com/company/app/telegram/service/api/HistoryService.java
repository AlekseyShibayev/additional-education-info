package com.company.app.telegram.service.api;

import com.company.app.telegram.entity.Chat;

public interface HistoryService {

	void save(String text);

	void save(Chat chat, String text);

	void save(Long chatId, String text);
}
