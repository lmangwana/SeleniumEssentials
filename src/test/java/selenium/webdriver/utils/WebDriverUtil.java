package selenium.webdriver.utils;

import io.appium.java_client.safari.SafariDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverUtil {
    private static WebDriver driver;
    private static final String BASE_URL = "https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php";

    public static WebDriver initDriver(String browserName) {
        driver = createDriver(browserName);
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        return driver;
    }
    public static WebDriver createDriver(String browserName) {
        return switch (browserName.toLowerCase()) {
            case "firefox" -> new FirefoxDriver();
            case "edge" -> new EdgeDriver();
            default -> new ChromeDriver();
        };
    }
}
