package com.company.app.exchangerate.component.impl;

import com.company.app.core.tool.impl.DataExtractorToolImpl;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExchangeRateExtractorImplTest {
	private static final String FILE_NAME = "exchangeRate/AliexpressUsdResponseExample.html";

	private ExchangeRateExtractorImpl exchangeExtractorService;
	private DataExtractorToolImpl dataExtractorService;

	@BeforeEach
	public void init() {
		exchangeExtractorService = new ExchangeRateExtractorImpl();
		dataExtractorService = new DataExtractorToolImpl();
		exchangeExtractorService.setDataExtractorTool(dataExtractorService);
	}

	@SneakyThrows
	@Test
	public void canExtractCurseFromHtmlResponseTest() {
		String fileAsString = dataExtractorService.getFileAsString(FILE_NAME);
		Assertions.assertEquals("78.17", exchangeExtractorService.getExchangeRate(fileAsString));
	}
}