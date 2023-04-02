package com.company.app.core.tools.api;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Map;

@Service
public interface DataExtractorService {

	String getFile(String fileName);

	List<File> getFiles(String packageName);

	Map<String, String> getProperties(String fileName);

	String getHtmlResponse(String urlName);

	JSONObject getJsonObject(JSONObject jsonObject, String searchString);

	JSONArray getJsonArray(JSONObject jsonObject, String searchString);
}
