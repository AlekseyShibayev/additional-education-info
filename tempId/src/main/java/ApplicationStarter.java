import Bots.TelegramBot;
import MainLogic.ApplicationHelper;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class ApplicationStarter {

	public static void main(String[] args) {
		try {
			//todo use spring context
			ApplicationHelper applicationHelper = new ApplicationHelper();

			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			TelegramBot telegramBot = new TelegramBot(applicationHelper);

			botsApi.registerBot(telegramBot);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
