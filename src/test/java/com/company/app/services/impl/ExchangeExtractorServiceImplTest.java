package com.company.app.services.impl;

import com.company.app.tools.impl.DataExtractorServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ExchangeExtractorServiceImplTest {

    private ExchangeExtractorServiceImpl exchangeExtractorService;
    private DataExtractorServiceImpl dataExtractorService;

    @Before
    public void init(){
        exchangeExtractorService = new ExchangeExtractorServiceImpl();
        dataExtractorService = new DataExtractorServiceImpl();
        exchangeExtractorService.setDataExtractorService(dataExtractorService);
    }

    @Test
    public void extractCurseTest() throws IOException {
        exchangeExtractorService.extractCurse();
    }
}