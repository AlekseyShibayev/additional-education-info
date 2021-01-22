package Services;

import MainLogic.TradingLot;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Date;

public class HtmlParserService {

    public TradingLot createTradingLot(String htmlResponse) {
        TradingLot tradingLot = new TradingLot();
        try {
            Element element = getFirstElement(htmlResponse);
            tradingLot.setName(getValue(element.select("div"), 0));
            tradingLot.setLocation(getValue(element.select("div"), 3));
            tradingLot.setGuild(getValue(element.select("div"), 4));
            tradingLot.setPrice(getValue(element.select("span"), 1));
            tradingLot.setLastSeen(getValue(element.select("td"), 4));
            tradingLot.setCreatedDate(new Date());
            return tradingLot;
        } catch (Exception e) {
            //todo throw Exception
            return tradingLot.getEmptyTradingLot();
        }
    }

    private String getValue(Elements elements, int index) {
        return elements.get(index).ownText();
    }

    private Element getFirstElement(String htmlResponse) {
        Document document = Jsoup.parse(htmlResponse);
        document = Jsoup.parse(document.getElementById("search-result-view").outerHtml());
        Element table = document.select("tbody").first();
        Elements rows = table.select("tr");
        return rows.get(0);
    }
}
