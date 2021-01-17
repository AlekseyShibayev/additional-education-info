package Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HtmlResponseExtractorService {

    private static final String USER_AGENT = "Mozilla/5.0";

    public String extractHtmlResponse(String urlName) throws IOException {
        return getHtmlResponse(urlName);
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
            throw new RuntimeException("http request not 200");
        }
        return response.toString();
    }
}
