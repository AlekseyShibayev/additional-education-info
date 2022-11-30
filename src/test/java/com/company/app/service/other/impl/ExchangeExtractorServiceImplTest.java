package com.company.app.service.other.impl;

import com.company.app.service.tools.impl.DataExtractorServiceImpl;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

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
	public void extractCurseTest() throws IOException {
		String fileAsString = dataExtractorService.getFileAsString(FILE_NAME);
		Assert.assertEquals("65.85", exchangeExtractorService.getExchange(fileAsString));
	}
}