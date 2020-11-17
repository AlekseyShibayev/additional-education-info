import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRunnable implements Runnable {

	private static final String USER_AGENT = "Mozilla/5.0";
	private static final long SLEEP_TIME = 15000;

	private Set<TradingLot> set;

	public void run() {
		try {
			doLogic();
		} catch (Exception e) {
			// :)
		}
	}

	private void doLogic() throws Exception {
		List<String> urls = getUrls();
		for (String url : urls) {
			//todo rundomizer
			sendGET(url);
			Thread.sleep(SLEEP_TIME);
		}
	}

	private void sendGET(String urlName) throws IOException {
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
			System.out.println(tradingLot);
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

	private List<String> getUrls() {
		List<String> urls = new ArrayList<>();
		urls.add("https://eu.tamrieltradecentre.com/pc/Trade/SearchResult?SearchType=Sell&ItemID=10039&ItemNamePattern=%D0%9A%D1%83%D1%88%D0%B0%D0%BA+%D0%BC%D0%B0%D1%82%D0%B5%D1%80%D0%B8%D0%BD%D1%81%D0%BA%D0%BE%D0%B9+%D1%81%D0%BA%D0%BE%D1%80%D0%B1%D0%B8&ItemCategory1ID=&ItemTraitID=13&ItemQualityID=&IsChampionPoint=false&LevelMin=160&LevelMax=&MasterWritVoucherMin=&MasterWritVoucherMax=&AmountMin=&AmountMax=&PriceMin=&PriceMax=10000&SortBy=LastSeen&Order=desc");
		urls.add("https://eu.tamrieltradecentre.com/pc/Trade/SearchResult?SearchType=Sell&ItemID=8272&ItemNamePattern=%D0%9F%D0%B0%D1%81%D0%BB%D0%B5%D0%BD&ItemCategory1ID=&ItemTraitID=&ItemQualityID=&IsChampionPoint=false&LevelMin=&LevelMax=&MasterWritVoucherMin=&MasterWritVoucherMax=&AmountMin=30&AmountMax=&PriceMin=&PriceMax=110");
		urls.add("https://eu.tamrieltradecentre.com/pc/Trade/SearchResult?SearchType=Sell&ItemID=1823&ItemNamePattern=%D0%9B%D1%83%D0%B3%D0%BE%D0%B2%D0%BE%D0%B9+%D1%81%D0%B5%D1%80%D0%B4%D0%B5%D1%87%D0%BD%D0%B8%D0%BA&ItemCategory1ID=&ItemTraitID=&ItemQualityID=&IsChampionPoint=false&LevelMin=&LevelMax=&MasterWritVoucherMin=&MasterWritVoucherMax=&AmountMin=30&AmountMax=&PriceMin=&PriceMax=180");
		urls.add("https://eu.tamrieltradecentre.com/pc/Trade/SearchResult?SearchType=Sell&ItemID=511&ItemNamePattern=%D0%92%D0%B0%D1%81%D0%B8%D0%BB%D0%B5%D0%BA&ItemCategory1ID=&ItemTraitID=&ItemQualityID=&IsChampionPoint=false&LevelMin=&LevelMax=&MasterWritVoucherMin=&MasterWritVoucherMax=&AmountMin=30&AmountMax=&PriceMin=&PriceMax=350");
		urls.add("https://eu.tamrieltradecentre.com/pc/Trade/SearchResult?SearchType=Sell&ItemID=8182&ItemNamePattern=%D0%A1%D0%BA%D1%80%D0%B8%D0%B1%D0%BE%D0%B2%D0%BE%D0%B5+%D0%B6%D0%B5%D0%BB%D0%B5&ItemCategory1ID=&ItemTraitID=&ItemQualityID=&IsChampionPoint=false&LevelMin=&LevelMax=&MasterWritVoucherMin=&MasterWritVoucherMax=&AmountMin=30&AmountMax=&PriceMin=&PriceMax=200");
		urls.add("https://eu.tamrieltradecentre.com/pc/Trade/SearchResult?SearchType=Sell&ItemID=&ItemNamePattern=%D0%B2%D0%BE%D0%B4%D0%BE%D1%81%D0%B1%D0%BE%D1%80&ItemCategory1ID=&ItemTraitID=&ItemQualityID=&IsChampionPoint=false&LevelMin=&LevelMax=&MasterWritVoucherMin=&MasterWritVoucherMax=&AmountMin=30&AmountMax=&PriceMin=&PriceMax=250&SortBy=LastSeen&Order=desc");
		urls.add("https://eu.tamrieltradecentre.com/pc/Trade/SearchResult?SearchType=Sell&ItemID=5661&ItemNamePattern=%D0%91%D0%B5%D1%80%D0%B2%D0%B5%D0%B7%D0%BD%D1%8B%D0%B9+%D1%81%D0%BE%D0%BA&ItemCategory1ID=&ItemTraitID=&ItemQualityID=&IsChampionPoint=false&LevelMin=&LevelMax=&MasterWritVoucherMin=&MasterWritVoucherMax=&AmountMin=20&AmountMax=&PriceMin=&PriceMax=100");
		urls.add("https://eu.tamrieltradecentre.com/pc/Trade/SearchResult?SearchType=Sell&ItemID=1114&ItemNamePattern=%D0%9A%D1%83%D1%82%D0%B0&ItemCategory1ID=&ItemTraitID=&ItemQualityID=&IsChampionPoint=false&LevelMin=&LevelMax=&MasterWritVoucherMin=&MasterWritVoucherMax=&AmountMin=5&AmountMax=&PriceMin=&PriceMax=1600");
		return urls;
	}
}