package MainLogic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class ApplicationStarter {

	private static final String SPRING_XML_PATH = "config.xml";

	public static void main(String[] args) {
		try {
			ApplicationContext context = new ClassPathXmlApplicationContext(SPRING_XML_PATH);

			TelegramBot telegramBot = context.getBean(TelegramBot.class);
			telegramBot.init();

			SearchTask searchTask = context.getBean(SearchTask.class);
			searchTask.init();

			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(telegramBot);

			searchTask.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
