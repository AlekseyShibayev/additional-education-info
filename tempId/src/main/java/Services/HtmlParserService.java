package Services;

import MainLogic.TradingLot;

import java.util.ArrayList;
import java.util.List;

public class HtmlParserService {

    public TradingLot createTradingLot(String htmlResponse) {
        //todo use normal html parser
        TradingLot tradingLot = new TradingLot();
        try {
            String htmlString = getStringWithLastTradingLot(htmlResponse);
            List<String> strings = new ArrayList<>();
            String[] split = htmlString.split(" <td class");
            for (String s : split) {
                String[] divSplit = s.split("</div>");
                for (String s2 : divSplit) {
                    strings.add(s2);
                }
            }
            tradingLot.fillAndGet(strings);
        } catch (Exception e) {
            return tradingLot;
        }
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
