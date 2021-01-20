package Services;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

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
