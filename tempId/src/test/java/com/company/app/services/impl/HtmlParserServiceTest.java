package com.company.app.services.impl;

import com.company.app.mainLogic.TradingLot;

import com.company.app.services.api.HtmlParserService;
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
    private static final String LOT_PRICE = "900,00";
    private static final String LOT_LAST_SEEN = "26 Minute ago";

    @Test
    public void createTradingLotTest() throws Exception {
        HtmlParserService htmlParserService = new HtmlParserServiceImpl();
        String testHtmlResponse = getTestHtmlResponse();
        TradingLot tradingLot = htmlParserService.createTradingLot(testHtmlResponse);

        Assert.assertEquals(tradingLot.getName(), LOT_NAME);
        Assert.assertEquals(tradingLot.getLocation(), LOT_LOCATION);
        Assert.assertEquals(tradingLot.getGuild(), LOT_GUILD);
        Assert.assertEquals(tradingLot.getPrice(), LOT_PRICE);
        Assert.assertEquals(tradingLot.getLastSeen(), LOT_LAST_SEEN);
        Assert.assertEquals(tradingLot.isReadyToPrint(), true);
    }

    private String getTestHtmlResponse() throws Exception {
        String result;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(FILE_NAME)) {
            result = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        }
        return result;
    }

    @Test
    public void isLastSeenGoodTest() throws Exception {
        HtmlParserServiceImpl htmlParserService = new HtmlParserServiceImpl();
        String lastSeen = "13 Minute ago";
        String lastSeen2 = "61 Minute ago";
        Assert.assertTrue(htmlParserService.isLastSeenGood(lastSeen));
        Assert.assertFalse(htmlParserService.isLastSeenGood(lastSeen2));
    }
}


