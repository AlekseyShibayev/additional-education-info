package com.company.app.services.api;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface DataExtractorService {

    Map<String, String> getProperties(String fileName);

    String getHtmlResponse(String urlName);
}
