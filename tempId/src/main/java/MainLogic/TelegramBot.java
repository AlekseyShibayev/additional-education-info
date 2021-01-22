package MainLogic;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;

public class TelegramBot extends TelegramLongPollingCommandBot {

    private static final String CHAT_PROPERTIES = "chat.properties";
    private static final String TELEGRAM_PROPERTIES = "telegram.properties";
    private static final String NAME = "name";
    private static final String TOKEN = "token";
    private static final String START_COMMAND = "startCommand";

    private ApplicationHelper applicationHelper;
    private Map<Long, String> chats;
    private String name;
    private String token;
    private String startCommand;

    public TelegramBot(ApplicationHelper applicationHelper) {
        this.applicationHelper = applicationHelper;
        Map<String, String> telegramProperties = applicationHelper.getDataExtractorService().getProperties(TELEGRAM_PROPERTIES);
        this.name = telegramProperties.get(NAME);
        this.token = telegramProperties.get(TOKEN);
        this.startCommand = telegramProperties.get(START_COMMAND);
        this.chats = getChats(applicationHelper);
    }

    private Map<Long, String> getChats(ApplicationHelper applicationHelper) {
        Map<String, String> chatProperties = applicationHelper.getDataExtractorService().getProperties(CHAT_PROPERTIES);
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

    /**
     * Ответ на запрос, не являющийся командой
     */
    @Override
    public void processNonCommandUpdate(Update update) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String text = message.getText();

        if (chats.containsKey(chatId) && startCommand.equals(text)) {
            //todo add listener
            startSearch();
        } else {
            System.out.println(text + " " + chatId);
        }
    }

    private void startSearch() {
        applicationHelper.getNotificationService().setTelegramBot(this);
        SearchTask task = new SearchTask(applicationHelper);
        task.run();
    }

    public Map<Long, String> getChats() {
        return chats;
    }
}
