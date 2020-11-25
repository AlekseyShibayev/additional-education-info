package Services;

import MainLogic.TradingLot;

import java.util.ArrayList;
import java.util.List;

public class HtmlParserService {

    public TradingLot createTradingLot(String htmlResponse) {
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

    //todo need work with last lot time, say about lot if created time <30 min
    private String getStringWithLastTradingLot(String htmlResponse) {
        //<tr class="cursor-pointer"
        String[] split = htmlResponse.split("<tr class=\"cursor-pointer\"");
        // we need always second
        return split[1];
    }
}
