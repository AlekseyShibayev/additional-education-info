package com.company.app.services.impl;

import com.company.app.services.api.DataExtractorService;
import com.google.common.collect.Maps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

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
    public String getHtmlResponse(String urlName) {
        try {
            return  getHtmlPage(urlName);
        } catch (InterruptedException e) {
            throw new RuntimeException("Can't get html.");
        }
    }

    private String getHtmlPage(String urlName) throws InterruptedException {

        System.setProperty("webdriver.gecko.driver", "tempId\\src\\main\\resources\\drivers\\geckodriver.exe");
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
