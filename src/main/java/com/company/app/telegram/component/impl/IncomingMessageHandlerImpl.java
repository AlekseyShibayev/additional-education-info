package com.company.app.telegram.component.impl;

import com.company.app.telegram.component.api.BinderExecutor;
import com.company.app.telegram.component.api.ChatActivationService;
import com.company.app.telegram.component.api.IncomingMessageHandler;
import com.company.app.telegram.component.api.TelegramBotConfig;
import com.company.app.telegram.component.data.ButtonAndCommandRegistry;
import com.company.app.telegram.entity.Chat;
import com.company.app.telegram.service.api.ChatService;
import com.company.app.telegram.service.api.HistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
public class IncomingMessageHandlerImpl implements IncomingMessageHandler {

	@Autowired
	TelegramBotConfig telegramBotConfig;
	@Autowired
	HistoryService historyService;
	@Autowired
	ChatService chatService;
	@Autowired
	BinderExecutor binderExecutor;
	@Autowired
	ChatActivationService chatActivationService;

	@Override
	public void work(Update update) {
		if (isIncomingMessage(update)) {
			prepareToWork(update);
			showCommands(update);
		} else {
			handle(update);
		}
	}

	private static boolean isIncomingMessage(Update update) {
		return update.getMessage() != null;
	}

	private void prepareToWork(Update update) {
		Message message = update.getMessage();
		Long chatId = message.getChatId();
		String text = message.getText();

		Chat chat = chatService.getChatOrCreateIfNotExist(chatId);
		log.debug("Читаю из чата [{}] сообщение [{}].", chatId, text);
		historyService.save(chat, text);

		chatActivationService.fullActivate(chat);
	}

	private void showCommands(Update update) {
		SendMessage sendMessage = new SendMessage();
		sendMessage.setChatId(update.getMessage().getChatId());
		sendMessage.setText("Выбирай:");
		sendMessage.setReplyMarkup(ButtonAndCommandRegistry.inlineMarkup());
		telegramBotConfig.write(sendMessage);
	}

	private void handle(Update update) {
		if (update.hasCallbackQuery()) {
			CallbackQuery callbackQuery = update.getCallbackQuery();
			Long chatId = callbackQuery.getMessage().getChatId();
			String data = callbackQuery.getData();
			log.debug("Читаю из чата [{}] сообщение [{}].", chatId, data);
			Chat chat = chatService.getChatOrCreateIfNotExist(chatId);
			binderExecutor.execute(chat, data);
		}
	}
}
