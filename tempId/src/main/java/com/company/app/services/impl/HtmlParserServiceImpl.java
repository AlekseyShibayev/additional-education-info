package com.company.app.services.impl;

import com.company.app.mainLogic.TradingLot;
import com.company.app.services.api.HtmlParserService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HtmlParserServiceImpl implements HtmlParserService {
    private static final String EMPTY = "empty";
    private static final String NOW = "Now";
    private static final String MINUTE = "Minute";
    private static final int VALUE = 60;

    @Override
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
            tradingLot.setReadyToPrint(getCheckResult(tradingLot));
            return tradingLot;
        } catch (Exception e) {
            return getEmptyTradingLot();
        }
    }

    private boolean getCheckResult(TradingLot tradingLot) {
        boolean result = false;
        if (tradingLot.getGuild().equals("")
                || tradingLot.getLocation().equals("")
                || tradingLot.getName().equals("")) {
            result = false;
        } else {
            String lastSeen = tradingLot.getLastSeen();
            if (lastSeen.contains(NOW)) {
                result = true;
            }
            if (lastSeen.contains(MINUTE)) {
                result = isLastSeenGood(lastSeen);
            }
        }
        return result;
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

    private TradingLot getEmptyTradingLot() {
        TradingLot tradingLot = new TradingLot();
        tradingLot.setName(EMPTY);
        tradingLot.setLocation(EMPTY);
        tradingLot.setGuild(EMPTY);
        tradingLot.setPrice(EMPTY);
        tradingLot.setLastSeen(EMPTY);
        tradingLot.setReadyToPrint(false);
        return tradingLot;
    }

    boolean isLastSeenGood(String lastSeen) {
        boolean result = false;
        String[] array = lastSeen.split("Minute");
        if (Integer.parseInt(array[0].trim()) < VALUE) {
            result = true;
        }
        return result;
    }
}
