package com.company.app.service.telegram.impl;

import com.company.app.entity.Exchange;
import com.company.app.entity.History;
import com.company.app.repository.ExchangeRepository;
import com.company.app.repository.HistoryRepository;
import com.company.app.service.other.api.NotificationService;
import com.company.app.service.telegram.api.TelegramHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Date;

@Component
public class TelegramHandlerImpl implements TelegramHandler {

	@Autowired
	private HistoryRepository historyRepository;
	@Autowired
	private ExchangeRepository exchangeRepository;
	@Autowired
	private TelegramBotServiceImpl telegramBotService;
	@Autowired
	private NotificationService notificationService;

	@Override
	public void process(Update update) {
		Message message = update.getMessage();
		Long chatId = message.getChatId();
		String text = message.getText();

		historyRepository.save(History.builder()
				.message(text)
				.date(new Date())
				.build());

		System.out.println(chatId + ": " + text);

		if (text.equals("1")) {
			Exchange exchange = exchangeRepository.findAllByOrderByDateDesc().get(0);
			notificationService.eventNotification(exchange);
		}
	}

	@Override
	public void execute(SendMessage answer) {
		try {
			telegramBotService.execute(answer);
		} catch (Exception e) {
			throw new RuntimeException("NotificationService can't write messages.", e);
		}
	}
}
