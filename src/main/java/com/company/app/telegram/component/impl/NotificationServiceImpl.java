package com.company.app.telegram.component.impl;

import com.company.app.telegram.TelegramFacade;
import com.company.app.telegram.component.api.NotificationService;
import com.company.app.telegram.entity.History;
import com.company.app.telegram.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Date;

@Component
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private HistoryRepository historyRepository;
	@Autowired
	private TelegramFacade telegramFacade;

	public void eventNotification(Object message) {
		History history = History.builder()
				.message(String.valueOf(message))
				.date(new Date())
				.build();
		historyRepository.save(history);

		telegramFacade.getChats().keySet().stream()
				.map(chatId -> getSendMessage(message, chatId))
				.forEach(telegramFacade::execute);
	}

	private SendMessage getSendMessage(Object message, Long chatId) {
		SendMessage answer = new SendMessage();
		answer.setText(message.toString());
		answer.setChatId(chatId.toString());
		return answer;
	}
}
