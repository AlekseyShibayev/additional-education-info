package com.company.app.service.telegram.components.impl;

import com.company.app.entity.Exchange;
import com.company.app.entity.History;
import com.company.app.repository.ExchangeRepository;
import com.company.app.repository.HistoryRepository;
import com.company.app.service.application.main.api.NotificationService;
import com.company.app.service.telegram.components.api.TelegramHandler;
import com.company.app.service.wildberries.components.WildberriesBinder;
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
	@Autowired
	private WildberriesBinder wildberriesBinder;

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

		if (text.startsWith("$1")) {
			Exchange exchange = exchangeRepository.findAllByOrderByDateDesc().get(0);
			notificationService.eventNotification(exchange);
		} else if (text.startsWith("$2")) {
			wildberriesBinder.bind(text);
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
