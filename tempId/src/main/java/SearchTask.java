import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class SearchTask implements Runnable {

	private static final String USER_AGENT = "Mozilla/5.0";
	private static final long SLEEP_TIME = 120_000;

	private Map<String, String> urls;
	private Set<TradingLot> lots;
	private int captchaCounter;

	public SearchTask() {
		this.lots = new HashSet<>();
		this.captchaCounter = 0;
		this.urls = FileDataExtractor.extract();
	}

	public void run() {
		try {
			while (true) {
				doLogic();
				incrementCaptchaCounter();
				NotificationService.eventNotification(". . . . . . . . .");
			}
        } catch (Exception e) {
			NotificationService.eventNotification(String.valueOf(captchaCounter));
			NotificationService.errorNotification(e);
			Thread.currentThread().interrupt();
		}
	}

	private void incrementCaptchaCounter() {
		captchaCounter++;
	}

	private void doLogic() throws Exception {
        for (Map.Entry<String, String> entry : urls.entrySet()) {
            //todo randomizer
            executeSearch(entry.getKey(), entry.getValue());
            Thread.sleep(SLEEP_TIME);
        }
	}

	private void executeSearch(String lotName, String urlName) throws IOException {
		String htmlResponse = getHtmlResponse(urlName);
		TradingLot tradingLot = HtmlParser.createTradingLot(htmlResponse);

		if (!lots.contains(tradingLot)) {
			lots.add(tradingLot);
			NotificationService.eventNotification(lotName + ": " + tradingLot);
		}
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
			NotificationService.eventNotification("GET request not worked");
		}
		return response.toString();
	}
}