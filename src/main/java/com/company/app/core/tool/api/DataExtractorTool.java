package com.company.app.core.tool.api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface DataExtractorTool {

	String getFileAsString(String fileName);

	List<File> getFiles(String packageName);

	Map<String, String> getProperties(String fileName);

	String getHtmlResponse(String urlName);

	JSONObject getJsonObject(JSONObject jsonObject, String searchString);

	JSONArray getJsonArray(JSONObject jsonObject, String searchString);
}
