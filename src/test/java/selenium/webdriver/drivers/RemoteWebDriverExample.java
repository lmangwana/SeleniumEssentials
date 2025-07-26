package selenium.webdriver.drivers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class RemoteWebDriverExample {
    /**
     * ✅ What is RemoteWebDriver?
     * 	•	Used to run Selenium tests on a different machine (called the node).
     * 	•	The client machine runs the test code.
     * 	•	The remote machine runs the browser.
     * 	•	They connect via a Selenium Grid (hub + nodes).
     */

    /**
     * ✅ Prerequisites
     * 	1.	Start Selenium Grid server (hub + node)
     * 	2.	Get the Grid Hub URL
     * 	3. Options
     */

    public static void remoteWebDriverDemo() throws MalformedURLException {
        // 1. Define the Grid hub URL
        URL gridUrl = new URL("http://192.168.0.10:4444/wd/hub");

        // 2. Define browser capabilities /options (e.g., Chrome)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        // 3. Create RemoteWebDriver
        WebDriver driver = new RemoteWebDriver(gridUrl, options);

        // 4. Use it like any normal driver
        driver.get("https://www.google.com");
        System.out.println("Title: " + driver.getTitle());

        driver.quit();
    }

    public static void uploadFile(WebDriver driver, File uploadFile) {
        // 🔁 Cast to RemoteWebDriver to set LocalFileDetector
        ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());

        // 🔼 Upload input must accept file
        WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));
        fileInput.sendKeys(uploadFile.getAbsolutePath());

        // Click upload/submit button
        driver.findElement(By.id("file-submit")).click();
    }

    public static void downloadFile()
    {
        //1. Enable download support when creating the driver:

        //2. Upload a file from local system to remote browser:

        //3. Download or list files:
    }

}
