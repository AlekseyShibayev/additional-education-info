package com.company.app.services.impl;

import com.company.app.mainLogic.TelegramBotService;
import com.company.app.services.api.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

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
        bot.getChats().keySet().stream()
                .map((Function<Long, Object>) aLong -> {
                    SendMessage answer = new SendMessage();
                    answer.setText(message.toString());
                    answer.setChatId(aLong.toString());
                    return answer;
                })
                .forEach(bot::sendAll);
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
