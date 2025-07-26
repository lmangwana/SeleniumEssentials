package selenium.webdriver.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class Options {

    /**
     * In Selenium, Browser Options allow you to configure the browser before launching it —
     * like running in headless mode, disabling extensions, setting download directories, etc.
     *
     */

    /**
     * ✅ 2. ChromeOptions – Most Common
     *
     * 🔧 Common Use Cases:
     * 	•	Headless mode
     * 	•	Disable popups/notifications
     * 	•	Set download directory
     * 	•	Incognito mode
     */

    @Test
    public static void ChromeOptions()
    {
        /*
         * You pass these options to the driver constructor:
         * WebDriver driver = new ChromeDriver(chromeOptions);
         */

        ChromeOptions options = new ChromeOptions();

        // ✅ Headless mode
        options.addArguments("--headless");

        // ✅ Disable notifications
        options.addArguments("--disable-notifications");

        // ✅ Start maximized
        options.addArguments("--start-maximized");

        // ✅ Incognito mode
        options.addArguments("--incognito");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.google.com");

        System.out.println("Title: " + driver.getTitle());
        driver.quit();
    }



}
