package com.company.app.services.impl;

import org.junit.Test;

public class ExchangeExtractorServiceImplTest {

    @Test
    public void extractCurseTest() {
        ExchangeExtractorServiceImpl exchangeExtractorService = new ExchangeExtractorServiceImpl();
        exchangeExtractorService.extractCurse();
    }
}