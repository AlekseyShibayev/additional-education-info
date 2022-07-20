package com.company.app.tools.impl;

import com.company.app.tools.api.DataExtractorService;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

@Component
public class DataExtractorServiceImpl implements DataExtractorService {

    @Override
    public Map<String, String> getProperties(String fileName) {
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream stream = classLoader.getResourceAsStream(fileName)) {
            properties.load(stream);
        } catch (IOException e) {
            throw new RuntimeException("Can't read properties from file.");
        }
        return Maps.fromProperties(properties);
    }

    @Override
    public String getHtmlResponse(String urlName) throws IOException {
        StringBuilder response = new StringBuilder();
        URL url = new URL(urlName);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            fillResponse(connection, response);
        } else {
            throw new RuntimeException("http request is not 200.");
        }
        return response.toString();
    }

    private void fillResponse(HttpURLConnection con, StringBuilder response) throws IOException {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
    }
}