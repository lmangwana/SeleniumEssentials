package selenium.webdriver.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

public class Sessions {

    /**
     * Demonstrates how to retrieve and use the Session ID in Selenium.
     *
     * 🆔 What is SessionId?
     * - Every WebDriver session (local or remote) gets a unique ID.
     * - This SessionId is used internally to map commands to your browser instance.
     * - Especially useful in RemoteWebDriver or debugging scenarios.
     *
     * 🔍 When to use:
     * - When you want to track, log, or reconnect to a running session.
     * - When debugging flaky grid issues or analyzing test behavior.
     */

    /*
     * 🔍 Accessing getSessionId(): ChromeDriver vs WebDriver
     *
     * ✅ If you declare:
     *     ChromeDriver driver = new ChromeDriver();
     *   You CAN call:
     *     driver.getSessionId();
     *   Because ChromeDriver extends RemoteWebDriver, which defines getSessionId().
     *
     * ❌ But if you declare:
     *     WebDriver driver = new ChromeDriver();
     *   You CANNOT call:
     *     driver.getSessionId();
     *   Because WebDriver is just an interface, and getSessionId() is not part of it.
     *
     * 💡 Solution:
     *   - Either declare the driver as ChromeDriver or RemoteWebDriver
     *   - OR cast it when needed:
     *       SessionId id = ((RemoteWebDriver) driver).getSessionId();
     *
     * ✅ Use WebDriver for flexibility and browser abstraction
     * ✅ Use RemoteWebDriver or ChromeDriver when you need lower-level driver methods
     */
    public static void demonstrateSessionIdUsage() {
        WebDriver driver = new ChromeDriver();

        // ✅ Cast to RemoteWebDriver to access getSessionId()
        SessionId sessionId = ((RemoteWebDriver) driver).getSessionId();

        System.out.println("🔐 WebDriver Session ID: " + sessionId.toString());

        driver.get("https://www.selenium.dev/");
        System.out.println("Page Title: " + driver.getTitle());

        driver.quit();
    }
}
