package com.company.app.telegram.component.impl;

import com.company.app.exchangeRate.component.api.ExchangeRateBinder;
import com.company.app.telegram.component.api.ChatRegistry;
import com.company.app.telegram.component.api.TelegramBotConfig;
import com.company.app.telegram.component.api.TelegramService;
import com.company.app.telegram.entity.History;
import com.company.app.telegram.repository.HistoryRepository;
import com.company.app.wildberries.component.WildberriesBinder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Date;

@Slf4j
@Component
public class TelegramServiceImpl implements TelegramService {

	@Autowired
	HistoryRepository historyRepository;
	@Autowired
	TelegramBotConfig telegramBotConfig;
	@Autowired
	WildberriesBinder wildberriesBinder;
	@Autowired
	ExchangeRateBinder exchangeRateBinder;
	@Autowired
	ChatRegistry chatService;

	@Override
	public void read(Update update) {
		Message message = update.getMessage();
		Long chatId = message.getChatId();
		String text = message.getText();

		log.debug("[{}]: [{}].", chatId, text);

		historyRepository.save(History.builder()
				.message(text)
				.date(new Date())
				.build());

		if (text.startsWith("ER")) {
			exchangeRateBinder.bind(text);
		} else if (text.startsWith("WB")) {
			wildberriesBinder.bind(text);
		}
	}

	@SneakyThrows
	@Override
	public void write(Object message) {
		History history = History.builder()
				.message(String.valueOf(message))
				.date(new Date())
				.build();
		historyRepository.save(history);

		chatService.getChats().keySet().stream()
				.map(chatId -> getSendMessage(message, chatId))
				.forEach(telegramBotConfig::write);
	}

	private SendMessage getSendMessage(Object message, Long chatId) {
		SendMessage answer = new SendMessage();
		answer.setText(message.toString());
		answer.setChatId(chatId.toString());
		return answer;
	}
}
