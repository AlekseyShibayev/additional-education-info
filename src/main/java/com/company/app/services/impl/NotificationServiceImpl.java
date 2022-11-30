package com.company.app.services.impl;

import com.company.app.entity.History;
import com.company.app.repository.HistoryRepository;
import com.company.app.services.api.NotificationService;
import com.company.app.services.api.TelegramBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private TelegramBotService bot;
	@Autowired
	private HistoryRepository historyRepository;

	public void eventNotification(Object message) {

		History history = History.builder()
				.message(String.valueOf(message))
				.build();
		historyRepository.save(history);

		bot.getChats().keySet().stream()
				.map(chatId -> getSendMessage(message, chatId))
				.forEach(bot::sendAnswer);
	}

	private SendMessage getSendMessage(Object message, Long chatId) {
		SendMessage answer = new SendMessage();
		answer.setText(message.toString());
		answer.setChatId(chatId.toString());
		return answer;
	}
}
