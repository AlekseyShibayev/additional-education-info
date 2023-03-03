package com.company.app.service.exchangeRate.components.impl;

import com.company.app.service.application.tools.impl.DataExtractorServiceImpl;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExchangeRateExtractorImplTest {
	private static final String FILE_NAME = "AliexpressUsdResponseExample.html";

	private ExchangeRateExtractorImpl exchangeExtractorService;
	private DataExtractorServiceImpl dataExtractorService;

	@Before
	public void init() {
		exchangeExtractorService = new ExchangeRateExtractorImpl();
		dataExtractorService = new DataExtractorServiceImpl();
		exchangeExtractorService.setDataExtractorService(dataExtractorService);
	}

	@SneakyThrows
	@Test
	public void canExtractCurseFromHtmlResponseTest() {
		String fileAsString = dataExtractorService.getFileAsString(FILE_NAME);
		Assert.assertEquals("78.17", exchangeExtractorService.getExchangeRate(fileAsString));
	}
}