package com.company.app.service.application.main.impl;

import com.company.app.service.application.tools.impl.DataExtractorServiceImpl;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExchangeExtractorServiceImplTest {
	private static final String FILE_NAME = "AliexpressUsdResponseExample.html";

	private ExchangeExtractorServiceImpl exchangeExtractorService;
	private DataExtractorServiceImpl dataExtractorService;

	@Before
	public void init() {
		exchangeExtractorService = new ExchangeExtractorServiceImpl();
		dataExtractorService = new DataExtractorServiceImpl();
		exchangeExtractorService.setDataExtractorService(dataExtractorService);
	}

	@SneakyThrows
	@Test
	public void extractCurseTest() {
		String fileAsString = dataExtractorService.getFileAsString(FILE_NAME);
		Assert.assertEquals("78.17", exchangeExtractorService.getExchange(fileAsString));
	}
}