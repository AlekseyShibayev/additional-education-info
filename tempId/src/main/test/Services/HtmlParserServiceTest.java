package Services;

import MainLogic.TradingLot;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class HtmlParserServiceTest {

    private static final String FILE_NAME = "ResponseExample.html";
    private static final String LOT_NAME = "Sash of a Mother's Sorrow";
    private static final String LOT_LOCATION = "Stormhaven: Wayrest";
    private static final String LOT_GUILD = "RTE";

    @Test
    public void createTradingLotTest() throws Exception {
        HtmlParserService htmlParserService = new HtmlParserService();
        String testHtmlResponse = getTestHtmlResponse();
        TradingLot tradingLot = htmlParserService.createTradingLot(testHtmlResponse);

        Assert.assertEquals(tradingLot.getName(), LOT_NAME);
        Assert.assertEquals(tradingLot.getLocation(), LOT_LOCATION);
        Assert.assertEquals(tradingLot.getGuild(), LOT_GUILD);
    }

    private String getTestHtmlResponse() throws Exception {
        String result;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(FILE_NAME)) {
            result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        }
        return result;
    }
}


