package com.company.app.services.impl;

import com.company.app.services.api.ChatService;
import com.company.app.services.api.DataExtractorService;
import com.company.app.services.api.NotificationService;
import com.company.app.services.api.TelegramBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class TelegramBotServiceImpl extends TelegramLongPollingCommandBot implements TelegramBotService {

    private static final String TELEGRAM_PROPERTIES = "telegram.properties";
    private static final String NAME = "name";
    private static final String TOKEN = "token";

    private Map<Long, String> chats;
    private String name;
    private String token;

    @Autowired
    private DataExtractorService dataExtractorService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ChatService chatService;

    @PostConstruct
    public void init() throws TelegramApiException {
        Map<String, String> telegramProperties = dataExtractorService.getProperties(TELEGRAM_PROPERTIES);
        this.name = telegramProperties.get(NAME);
        this.token = telegramProperties.get(TOKEN);
        this.chats = chatService.getChats();
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String text = message.getText();
        System.out.println(chatId + ": " + text);
    }

    public Map<Long, String> getChats() {
        return chats;
    }

    public void sendAnswer(SendMessage answer) {
        try {
            this.execute(answer);
        } catch (TelegramApiException e) {
            throw new RuntimeException("NotificationService can't write messages.");
        }
    }
}
