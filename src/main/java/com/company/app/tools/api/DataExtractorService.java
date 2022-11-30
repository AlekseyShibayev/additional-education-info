package com.company.app.tools.api;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public interface DataExtractorService {

    String getFileAsString(String fileName);

    Map<String, String> getProperties(String fileName);

    String getHtmlResponse(String urlName);

    JSONObject getJsonObject(JSONObject jsonObject, String searchString);
}
