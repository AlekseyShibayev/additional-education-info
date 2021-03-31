package com.company.app.services.impl;

import com.company.app.services.api.DataExtractorService;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
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
}
