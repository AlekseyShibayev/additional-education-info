package Services;

import MainLogic.TradingLot;

import java.util.ArrayList;
import java.util.List;

public class HtmlParserService {

    public static TradingLot createTradingLot(String htmlResponse) {
        //todo use normal html parser
        String htmlString = getStringWithLastTradingLot(htmlResponse);

        List<String> strings = new ArrayList<>();
        String[] split = htmlString.split(" <td class");
        for (String s : split) {
            String[] divSplit = s.split("</div>");
            for (String s2 : divSplit) {
                strings.add(s2);
            }
        }
        TradingLot tradingLot = new TradingLot();
        tradingLot.fillAndGet(strings);
        return tradingLot;
    }

    private static String getStringWithLastTradingLot(String htmlResponse) {
        String[] split = htmlResponse.split("<tr class=\"cursor-pointer\"");
        // we need always second
        return split[1];
    }
}
