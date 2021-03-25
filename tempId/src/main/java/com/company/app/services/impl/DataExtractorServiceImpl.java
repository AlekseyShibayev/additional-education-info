package com.company.app.services.impl;

import com.company.app.services.api.DataExtractorService;
import com.google.common.collect.Maps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Component
public class DataExtractorServiceImpl implements DataExtractorService {

    private static final String FIRST_DRIVER_PATH = "drivers/geckodriver";
    private static final String SECOND_DRIVER_PATH = "/usr/local/bin/geckodriver";

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
    public String getHtmlResponse(String urlName) {
        try {
            return  getHtmlPage(urlName);
        } catch (InterruptedException e) {
            throw new RuntimeException("Can't get html.");
        }
    }

    private String getHtmlPage(String urlName) throws InterruptedException {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL path = loader.getResource(FIRST_DRIVER_PATH);
        File file = new File(path.getPath());
        if (file.exists()) {
            System.setProperty("webdriver.gecko.driver", path.getPath());
        } else {
            System.setProperty("webdriver.gecko.driver", SECOND_DRIVER_PATH);
        }

        System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setHeadless(true);

        WebDriver driver = new FirefoxDriver(firefoxOptions);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();

        driver.get(urlName);

        Thread.sleep(15000);

        String pageSource = driver.getPageSource();
        driver.quit();
        return pageSource;
    }
}
