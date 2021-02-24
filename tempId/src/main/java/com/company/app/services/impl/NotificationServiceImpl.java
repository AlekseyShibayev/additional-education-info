package com.company.app.services.impl;

import com.company.app.mainLogic.TelegramBotService;
import com.company.app.services.api.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class NotificationServiceImpl implements NotificationService {

    //todo stateless
    private static final boolean logEnabled = true;
    private List<String> log;
    @Autowired
    private TelegramBotService bot;

    public NotificationServiceImpl() {
        this.log = new ArrayList<>();
    }

    public void eventNotification(Object message) {
        Map<Long, String> chats = bot.getChats();
        SendMessage answer = new SendMessage();
        answer.setText(message.toString());
        sendToALL(chats, answer);
    }

    private void sendToALL(Map<Long, String> chats, SendMessage answer) {
        try {
            for (Map.Entry<Long, String> entry : chats.entrySet()) {
                answer.setChatId(entry.getKey().toString());
                bot.execute(answer);
            }
        } catch (TelegramApiException e) {
            throw new RuntimeException("NotificationService can't write messages.");
        }
    }

    public void logNotification(Object message) {
        if (logEnabled) {
            log.add(message.toString());
        }
    }

    public void showLog() {
        for (String s : log) {
            System.out.println(s);
        }
    }

    public void errorNotification(Exception e) {
        e.printStackTrace();
    }
}
