package com.company.app.exchangeRate.component.impl;

import com.company.app.core.tools.impl.DataExtractorServiceImpl;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExchangeRateExtractorImplTest {
	private static final String FILE_NAME = "exchangeRate/AliexpressUsdResponseExample.html";

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
		String fileAsString = dataExtractorService.getFile(FILE_NAME);
		Assert.assertEquals("78.17", exchangeExtractorService.getExchangeRate(fileAsString));
	}
}