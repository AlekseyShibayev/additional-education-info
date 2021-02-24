package com.company.app;

import com.company.app.mainLogic.TradingLot;
import com.company.app.services.api.DataExtractorService;
import com.company.app.services.impl.DataExtractorServiceImpl;
import com.company.app.services.api.HtmlParserService;
import com.company.app.services.impl.HtmlParserServiceImpl;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class ApplicationTest {

    private static final String URL = "https://eu.tamrieltradecentre.com/pc/Trade/SearchResult?SearchType=Sell&ItemID=10039&ItemNamePattern=%D0%9A%D1%83%D1%88%D0%B0%D0%BA+%D0%BC%D0%B0%D1%82%D0%B5%D1%80%D0%B8%D0%BD%D1%81%D0%BA%D0%BE%D0%B9+%D1%81%D0%BA%D0%BE%D1%80%D0%B1%D0%B8&ItemCategory1ID=&ItemTraitID=13&ItemQualityID=&IsChampionPoint=false&LevelMin=160&LevelMax=&MasterWritVoucherMin=&MasterWritVoucherMax=&AmountMin=&AmountMax=&PriceMin=&PriceMax=8000";
    private static final String LOT_NAME = "Sash of a Mother's Sorrow";

    //TODO
    @Ignore
    @Test
    public void doFullApplicationTest() throws Exception {
        DataExtractorService dataExtractorService = new DataExtractorServiceImpl();
        HtmlParserService htmlParserService = new HtmlParserServiceImpl();

        String htmlResponse = dataExtractorService.getHtmlResponse(URL);
        TradingLot tradingLot = htmlParserService.createTradingLot(htmlResponse);

        Assert.assertEquals(tradingLot.getName(), LOT_NAME);
    }
}

