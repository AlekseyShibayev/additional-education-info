import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class MyRunnable implements Runnable {

	private static final String USER_AGENT = "Mozilla/5.0";
	private static final long SLEEP_TIME = 60_000;

	private Set<TradingLot> set;

	public void run() {
		try {
			doLogic();
        } catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doLogic() throws Exception {
		Map<String, String> urls = getUrls();
        for (Map.Entry<String, String> entry : urls.entrySet()) {
            //todo randomizer
            sendGET(entry.getKey(), entry.getValue());
            Thread.sleep(SLEEP_TIME);
        }
	}

	private void sendGET(String lotName, String urlName) throws IOException {
		String htmlResponse = getHtmlResponse(urlName);

		String[] split = htmlResponse.split("<tr class=\"cursor-pointer\"");
		// нам нужен всегда 1
		String htmlString = split[1];

		HtmlParser htmlParser = new HtmlParser();
		TradingLot tradingLot = htmlParser.createTradingLot(htmlString);

		if (set == null) {
			set = new HashSet<>();
		}

		if (!set.contains(tradingLot)) {
			set.add(tradingLot);
			System.out.println(lotName + ": " + tradingLot);
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
			// todo log
			System.out.println("GET request not worked");
		}
		return response.toString();
	}

	private Map<String, String> getUrls() {
	    //todo write from property file
		Map<String, String> urls = new HashMap<>();
		urls.put("Кушак матери", "https://eu.tamrieltradecentre.com/pc/Trade/SearchResult?SearchType=Sell&ItemID=10039&ItemNamePattern=%D0%9A%D1%83%D1%88%D0%B0%D0%BA+%D0%BC%D0%B0%D1%82%D0%B5%D1%80%D0%B8%D0%BD%D1%81%D0%BA%D0%BE%D0%B9+%D1%81%D0%BA%D0%BE%D1%80%D0%B1%D0%B8&ItemCategory1ID=&ItemTraitID=13&ItemQualityID=&IsChampionPoint=false&LevelMin=160&LevelMax=&MasterWritVoucherMin=&MasterWritVoucherMax=&AmountMin=&AmountMax=&PriceMin=&PriceMax=10000&SortBy=LastSeen&Order=desc");
        return urls;
	}
}