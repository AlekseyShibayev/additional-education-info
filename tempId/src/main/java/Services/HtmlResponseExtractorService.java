package Services;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class HtmlResponseExtractorService {

    public String extractHtmlResponse(String urlName) {
        try {
            return getHtmlResponse(urlName);
        } catch (Exception e) {
            return "null";
        }
    }

    private String getHtmlResponse(String urlName) throws Exception {
        //todo
        System.setProperty("webdriver.chrome.driver", "G:\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();

        WebDriver driver = new ChromeDriver(options);

        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(1, 1));
        driver.get(urlName);

        Thread.sleep(15000);

        String pageSource = driver.getPageSource();
        driver.quit();
        return pageSource;
    }
}
