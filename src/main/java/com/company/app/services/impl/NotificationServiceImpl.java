package com.company.app.services.impl;

import com.company.app.services.api.TelegramBotService;
import com.company.app.services.api.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private TelegramBotService bot;

    public void eventNotification(Object message) {
        bot.getChats().keySet().stream()
                .map(chatId -> {
                    return getSendMessage(message, chatId);
                })
                .forEach(bot::sendAnswer);
    }

    private SendMessage getSendMessage(Object message, Long chatId) {
        SendMessage answer = new SendMessage();
        answer.setText(message.toString());
        answer.setChatId(chatId.toString());
        return answer;
    }
}
