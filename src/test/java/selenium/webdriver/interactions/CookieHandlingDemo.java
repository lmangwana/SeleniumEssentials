package selenium.webdriver.interactions;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class CookieHandlingDemo {

    /**
     * 🍪 Working with Cookies in Selenium
     *
     * Cookies help websites remember user preferences, sessions, and states.
     * Selenium provides built-in APIs to:
     * - Add cookies
     * - Retrieve single or all cookies
     * - Delete cookies
     *
     * ⚠️ NOTE:
     * To add or read cookies, you must first navigate to a page within the domain of the cookie.
     */
    public static void demonstrateCookieOperations() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/blank.html");

        // 1️⃣ Add a single cookie to the browser
        Cookie cookie1 = new Cookie("foo", "bar");
        driver.manage().addCookie(cookie1);

        // 2️⃣ Get a cookie by name
        Cookie retrievedCookie = driver.manage().getCookieNamed("foo");
        System.out.println("Retrieved cookie: " + retrievedCookie.getName() + " = " + retrievedCookie.getValue());

        // 3️⃣ Add multiple cookies
        driver.manage().addCookie(new Cookie("test1", "cookie1"));
        driver.manage().addCookie(new Cookie("test2", "cookie2"));

        // 4️⃣ Get all cookies and print their details
        Set<Cookie> allCookies = driver.manage().getCookies();
        System.out.println("All cookies:");
        for (Cookie c : allCookies) {
            System.out.println("- " + c.getName() + ": " + c.getValue());
        }

        // 5️⃣ Delete a cookie by name
        driver.manage().deleteCookieNamed("test1");
        System.out.println("Deleted cookie: test1");

        // 6️⃣ Delete all cookies
        driver.manage().deleteAllCookies();
        System.out.println("All cookies deleted.");

        driver.quit();
    }
}