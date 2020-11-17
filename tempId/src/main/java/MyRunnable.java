import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MyRunnable implements Runnable {

	private static final String USER_AGENT = "Mozilla/5.0";
	private static final String GET_URL = "https://eu.tamrieltradecentre.com/pc/Trade/SearchResult?SearchType=Sell&ItemID=10039&ItemNamePattern=%D0%9A%D1%83%D1%88%D0%B0%D0%BA+%D0%BC%D0%B0%D1%82%D0%B5%D1%80%D0%B8%D0%BD%D1%81%D0%BA%D0%BE%D0%B9+%D1%81%D0%BA%D0%BE%D1%80%D0%B1%D0%B8&ItemCategory1ID=&ItemTraitID=13&ItemQualityID=&IsChampionPoint=false&LevelMin=160&LevelMax=&MasterWritVoucherMin=&MasterWritVoucherMax=&AmountMin=&AmountMax=&PriceMin=&PriceMax=10000&SortBy=LastSeen&Order=desc";
	private static final long SLEEP_TIME = 1000;

	public void run() {
		try {
			doLogic();
		} catch (Exception e) {
			//todo log
			e.printStackTrace();
		}
	}

	private void doLogic() throws Exception {
		//todo iter
		sendGET(GET_URL);
		Thread.sleep(SLEEP_TIME);
	}

	private void sendGET(String urlName) throws IOException {
		String htmlResponse = getHtmlResponse(urlName);
		String[] split = htmlResponse.split("data-mins-elapsed");

		List<String> strings = new ArrayList<>();
		for (String s : split) {
			String substring = s.substring(2, 6);
			String s1 = substring.replaceAll("\"", "");
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
}