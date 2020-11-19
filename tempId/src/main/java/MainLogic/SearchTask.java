package MainLogic;

import Services.CaptchaFighterService;
import Services.FileDataExtractorService;
import Services.HtmlParserService;
import Services.NotificationService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class SearchTask implements Runnable {

	private static final String USER_AGENT = "Mozilla/5.0";

	private Map<String, String> urls;
	private List<String> lotNames;
	private Set<TradingLot> lots;
	private int counter;
	private NotificationService notificationService;

	SearchTask() {
		this.lots = new HashSet<>();
		this.counter = 0;
		this.urls = FileDataExtractorService.extract();
		this.lotNames = getLotNames(urls);
		this.notificationService = new NotificationService();
	}

	public void run() {
		try {
			while (true) {
				if (counter == 0) {
					notificationService.eventNotification("Application started. Searching: " + lotNames);
					CaptchaFighterService.fight(30_000, 60_000);
				}

				doLogic();

				notificationService.eventNotification(". . . . . .");
				incrementCaptchaCounter();
				notificationService.logNotification("finished: " + counter);
				CaptchaFighterService.fight(500_000, 650_000);
			}
        } catch (Exception e) {
			notificationService.logNotification(String.valueOf(counter));
			notificationService.logNotification(e);
			notificationService.writeLog();
			Thread.currentThread().interrupt();
		}
	}

	private void incrementCaptchaCounter() {
		counter++;
	}

	private void doLogic() throws Exception {
		List<String> queue = CaptchaFighterService.getQueue(lotNames);
		notificationService.logNotification("try started with request order: " + queue.toString());
		workWithQueue(queue);
	}

	private void workWithQueue(List<String> queue) throws IOException, InterruptedException {
		for (String currentKey : queue) {
			notificationService.logNotification("try execute with: " + currentKey);
			executeSearch(currentKey, urls.get(currentKey));
			CaptchaFighterService.fight(30_000, 100_000);
		}
	}

	private void executeSearch(String lotName, String urlName) throws IOException {
		String htmlResponse = getHtmlResponse(urlName);
		TradingLot tradingLot = HtmlParserService.createTradingLot(htmlResponse);
		if (isNotFoundEarlier(tradingLot)) {
			doAction(lotName, tradingLot);
		}
	}

	private void doAction(String lotName, TradingLot tradingLot) {
		lots.add(tradingLot);
		if (counter != 0) {
            notificationService.eventNotification(lotName + ": " + tradingLot);
        }
	}

	private boolean isNotFoundEarlier(TradingLot tradingLot) {
		return !lots.contains(tradingLot);
	}

	private String getHtmlResponse(String urlName) throws IOException {
		URL obj = new URL(urlName);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		int responseCode = con.getResponseCode();
		StringBuilder response = new StringBuilder();

		if (responseCode == HttpURLConnection.HTTP_OK) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} else {
			notificationService.eventNotification("GET request not worked");
		}
		return response.toString();
	}

	private List<String> getLotNames(Map<String, String> map) {
		List<String> names = new ArrayList<>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			names.add(entry.getKey());
		}
		return names;
	}
}