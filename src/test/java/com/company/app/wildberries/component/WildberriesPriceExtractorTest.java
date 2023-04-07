package com.company.app.wildberries.component;

import com.company.app.core.tools.impl.DataExtractorServiceImpl;
import com.company.app.wildberries.component.impl.WildberriesPriceExtractorImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WildberriesPriceExtractorTest {

	private static final String FILE_NAME = "wildberries/WildberriesHtmlResponseExample.html";

	private WildberriesPriceExtractorImpl wildberriesPriceExtractor;
	private DataExtractorServiceImpl dataExtractorService;

	@Before
	public void init() {
		wildberriesPriceExtractor = new WildberriesPriceExtractorImpl();
		dataExtractorService = new DataExtractorServiceImpl();
		wildberriesPriceExtractor.setDataExtractorService(dataExtractorService);
	}

	@Test
	public void extract() {
		String fileAsString = dataExtractorService.getFileAsString(FILE_NAME);
		Assert.assertEquals("109900", wildberriesPriceExtractor.extract(fileAsString, "43409221"));
	}
}