package com.company.app.exchange_rate.component.impl;

import com.company.app.core.tool.impl.DataExtractorToolImpl;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExchangeRateExtractorImplTest {
	private static final String FILE_NAME = "exchangeRate/AliexpressUsdResponseExample.html";

	private ExchangeRateExtractorImpl exchangeExtractorService;
	private DataExtractorToolImpl dataExtractorService;

	@Before
	public void init() {
		exchangeExtractorService = new ExchangeRateExtractorImpl();
		dataExtractorService = new DataExtractorToolImpl();
		exchangeExtractorService.setDataExtractorTool(dataExtractorService);
	}

	@SneakyThrows
	@Test
	public void canExtractCurseFromHtmlResponseTest() {
		String fileAsString = dataExtractorService.getFileAsString(FILE_NAME);
		Assert.assertEquals("78.17", exchangeExtractorService.getExchangeRate(fileAsString));
	}
}