package Services;

import Bots.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotificationService {

    private static final boolean logEnabled = true;

    private List<String> log;
    private TelegramBot telegramBot;

    public NotificationService() {
        this.log = new ArrayList<>();
    }

    public void eventNotification(Object message) {
        Map<Long, String> chats = telegramBot.getChats();
        SendMessage answer = new SendMessage();
        answer.setText(message.toString());
        writeAll(chats, answer);

        System.out.println(message.toString());
    }

    private void writeAll(Map<Long, String> chats, SendMessage answer) {
        try {
            for (Map.Entry<Long, String> entry : chats.entrySet()) {
                answer.setChatId(entry.getKey().toString());
                telegramBot.execute(answer);
            }
        } catch (TelegramApiException e) {
            //todo
            //логируем сбой Telegram Bot API, используя userName
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

    public TelegramBot getTelegramBot() {
        return telegramBot;
    }

    public void setTelegramBot(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }
}
