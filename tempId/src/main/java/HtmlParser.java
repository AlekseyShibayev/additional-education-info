import java.util.ArrayList;
import java.util.List;

public class HtmlParser {

    public static TradingLot createTradingLot(String htmlResponse) {
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
        // we need second
        return split[1];
    }
}
