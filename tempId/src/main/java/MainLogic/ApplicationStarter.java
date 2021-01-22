package MainLogic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class ApplicationStarter {

	private static final String SPRING_XML_PATH = "config.xml";

	public static void main(String[] args) {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext(SPRING_XML_PATH);
			ApplicationHelper applicationHelper = context.getBean(ApplicationHelper.class);
			TelegramBot telegramBot = new TelegramBot(applicationHelper);
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(telegramBot);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
