package com.company.app.core.tools.impl;

import com.company.app.core.tools.JsonSearcher;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DataExtractorServiceImplTest {

	private static final String FILE_NAME = "core/JsonObjectExample.html";

	private DataExtractorServiceImpl dataExtractorService;

	@Before
	public void init() {
		dataExtractorService = new DataExtractorServiceImpl();
	}

	@SneakyThrows
	@Test
	public void jsonSearchTest() {
		String string = dataExtractorService.getFileAsString(FILE_NAME);
		JSONObject jsonObject = new JSONObject(string);
		JsonSearcher jsonSearcher = new JsonSearcher();
		jsonSearcher.doRecursiveSearch(jsonObject, "activityAmount");

		JSONObject result = jsonSearcher.getResult();
		Object value = result.getDouble("value");
		assertEquals("65.85", value.toString());
	}

	@Test
	public void getFiles() {
		List<File> files = dataExtractorService.getFiles("src/test/resources/core/forGetFilesTest");
		Assert.assertEquals(files.size(), 3);
	}
}