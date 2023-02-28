package com.company.app.service.wildberries.components;

import com.company.app.service.tools.impl.DataExtractorServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class WildberriesServiceImplTest {

	WildberriesPriceExtractor wildberriesPriceExtractor;
	DataExtractorServiceImpl dataExtractorService;
	WildberriesServiceImpl wildberriesService;

	@Before
	public void init() {
		wildberriesService = new WildberriesServiceImpl();
		dataExtractorService = new DataExtractorServiceImpl();
		wildberriesPriceExtractor = new WildberriesPriceExtractor();
		wildberriesService.setDataExtractorService(dataExtractorService);
		wildberriesService.setWildberriesPriceExtractor(wildberriesPriceExtractor);
	}

	@Ignore //todo
	@Test
	public void doMainLogicTest() {
		wildberriesService.doMainLogic();
	}
}