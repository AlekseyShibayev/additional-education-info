package com.company.app.core.tools.impl;

import com.company.app.core.tool.JsonSearcher;
import com.company.app.core.tool.impl.DataExtractorToolImpl;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

class DataExtractorServiceImplTest {

	private static final String FILE_NAME = "core/JsonObjectExample.html";

	private DataExtractorToolImpl dataExtractorService;

	@BeforeEach
	public void init() {
		dataExtractorService = new DataExtractorToolImpl();
	}

	@SneakyThrows
	@Test
	void jsonSearchTest() {
		String string = dataExtractorService.getFileAsString(FILE_NAME);
		JSONObject jsonObject = new JSONObject(string);
		JsonSearcher jsonSearcher = new JsonSearcher();
		jsonSearcher.doRecursiveSearch(jsonObject, "activityAmount");

		JSONObject result = jsonSearcher.getResult();
		Object value = result.getDouble("value");
		Assertions.assertEquals("65.85", value.toString());
	}

	@Test
	void getFiles() {
		List<File> files = dataExtractorService.getFiles("src/test/resources/core/forGetFilesTest");
		Assertions.assertEquals(3, files.size());
	}
}