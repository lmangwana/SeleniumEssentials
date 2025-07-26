package selenium.webdriver.interactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserNavigationDemo {

    /**
     * 🌐 Browser Navigation in Selenium
     *
     * Selenium provides multiple ways to control browser navigation,
     * simulating the browser's address bar, back, forward, and refresh buttons.
     *
     * ✅ Actions demonstrated in this method:
     *  1. Navigate to a URL (via get() and navigate().to())
     *  2. Go back in browser history
     *  3. Go forward in browser history
     *  4. Refresh the current page
     */
    public static void demonstrateNavigation() throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        // 1️⃣ Navigate to a page using driver.get() — the most common method
        driver.get("https://www.selenium.dev");

        // 2️⃣ Alternative: driver.navigate().to() — achieves the same as get()
        driver.navigate().to("https://www.selenium.dev/documentation");

        // Pause briefly for visibility (not required in real tests)
        Thread.sleep(1000);

        // 3️⃣ Go back in browser history
        driver.navigate().back();
        Thread.sleep(1000);

        // 4️⃣ Go forward in browser history
        driver.navigate().forward();
        Thread.sleep(1000);

        // 5️⃣ Refresh the current page
        driver.navigate().refresh();

        driver.quit();
    }
}
