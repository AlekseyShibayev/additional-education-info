package com.company.app.wildberries.component;

import com.company.app.core.tool.impl.DataExtractorToolImpl;
import com.company.app.wildberries.component.impl.WildberriesPriceExtractorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WildberriesPriceExtractorTest {

	private static final String FILE_NAME = "wildberries/WildberriesHtmlResponseExample.html";

	private WildberriesPriceExtractorImpl wildberriesPriceExtractor;
	private DataExtractorToolImpl dataExtractorService;

	@BeforeEach
	public void init() {
		wildberriesPriceExtractor = new WildberriesPriceExtractorImpl();
		dataExtractorService = new DataExtractorToolImpl();
		wildberriesPriceExtractor.setDataExtractorService(dataExtractorService);
	}

	@Test
	void extract() {
		String fileAsString = dataExtractorService.getFileAsString(FILE_NAME);
		Assertions.assertEquals("109900", wildberriesPriceExtractor.extract(fileAsString, "43409221"));
	}
}