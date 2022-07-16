package com.company.app.tools.api;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public interface DataExtractorService {

    Map<String, String> getProperties(String fileName);

    String getHtmlResponse(String urlName) throws IOException;
}
