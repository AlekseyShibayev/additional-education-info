package com.company.app.telegram.component.impl;

import com.company.app.telegram.component.api.NotificationService;
import com.company.app.exchangeRate.entity.ExchangeRate;
import com.company.app.exchangeRate.repository.ExchangeRepository;
import com.company.app.telegram.component.api.TelegramHandler;
import com.company.app.telegram.entity.History;
import com.company.app.telegram.repository.HistoryRepository;
import com.company.app.wildberries.component.WildberriesBinder;
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
			ExchangeRate exchange = exchangeRepository.findAllByOrderByDateDesc().get(0);
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
