package com.company.app.service.application.main.impl;

import com.company.app.entity.Exchange;
import com.company.app.repository.ExchangeRepository;
import com.company.app.service.application.tools.impl.DataExtractorServiceImpl;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ExchangeExtractorServiceImplTest {
	private static final String FILE_NAME = "AliexpressUsdResponseExample.html";

	private ExchangeExtractorServiceImpl exchangeExtractorService;
	private DataExtractorServiceImpl dataExtractorService;
	private ExchangeRepository exchangeRepository;

	@Before
	public void init() {
		exchangeExtractorService = new ExchangeExtractorServiceImpl();
		dataExtractorService = new DataExtractorServiceImpl();
		exchangeExtractorService.setDataExtractorService(dataExtractorService);

		exchangeRepository = Mockito.mock(ExchangeRepository.class);
	}

	@SneakyThrows
	@Test
	public void canExtractCurseFromHtmlResponseTest() {
		String fileAsString = dataExtractorService.getFileAsString(FILE_NAME);
		Assert.assertEquals("78.17", exchangeExtractorService.getExchange(fileAsString));
	}

	@Test
	public void canExtractExchangeTest() {
		Mockito.when(exchangeRepository.save(Mockito.any())).getMock();
		Exchange exchange = exchangeExtractorService.extractCurse();
		Assert.assertNotNull(exchange.getAliexpressExchange());
	}
}