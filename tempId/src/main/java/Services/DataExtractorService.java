package Services;

import com.google.common.collect.Maps;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class DataExtractorService {

    public Map<String, String> getProperties(String fileName) {
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream stream = classLoader.getResourceAsStream(fileName)) {
            properties.load(stream);
        } catch (IOException e) {
            //todo
        }
        return Maps.fromProperties(properties);
    }

    public String getHtmlResponse(String urlName) {
        try {
            return  getHtmlPage(urlName);
        } catch (InterruptedException e) {
            //todo
            return "null";
        }
    }

    private String getHtmlPage(String urlName) throws InterruptedException {
        //todo
        System.setProperty("webdriver.chrome.driver", "G:\\chromedriver.exe");

        System.setProperty("webdriver.chrome.silentOutput", "true");
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);

        ChromeOptions options = new ChromeOptions();

        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().setPosition(new Point(-2000, 0));
        driver.get(urlName);

        Thread.sleep(15000);

        String pageSource = driver.getPageSource();
        driver.quit();
        return pageSource;
    }
}
