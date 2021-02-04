package com.company.app.MainLogic;

import com.company.app.Services.DataExtractorService;
import com.company.app.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

public class TelegramBot extends TelegramLongPollingCommandBot {

    private static final String CHAT_PROPERTIES = "chat.properties";
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

    @PostConstruct
    public void init() throws TelegramApiException {
        Map<String, String> telegramProperties = dataExtractorService.getProperties(TELEGRAM_PROPERTIES);
        this.name = telegramProperties.get(NAME);
        this.token = telegramProperties.get(TOKEN);
        this.chats = getAllChats();

        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    private Map<Long, String> getAllChats() {
        Map<String, String> chatProperties = dataExtractorService.getProperties(CHAT_PROPERTIES);
        Map<Long, String> result = new HashMap<>();
        for (Map.Entry<String, String> entry : chatProperties.entrySet()) {
            result.put(Long.parseLong(entry.getKey()), entry.getValue());
        }
        return result;
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

        System.out.println(text + " " + chatId);
    }

    public Map<Long, String> getChats() {
        return chats;
    }
}
