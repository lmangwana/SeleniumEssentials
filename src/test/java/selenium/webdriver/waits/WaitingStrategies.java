package selenium.webdriver.waits;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitingStrategies {

    /*
     * 🔄 Waiting Strategies in Selenium
     *
     * One of the biggest causes of flaky tests is race conditions — where Selenium tries to interact
     * with elements before they are present or visible in the DOM.
     *
     * ✅ Example: Dynamic elements that are added or revealed after a button click
     *    - e.g., a new div or input appears *after* a delay.
     *
     * ❌ Bad Practice: Thread.sleep() — can be too short (flaky) or too long (slow tests).
     *
     * ✅ Best Practice:
     *    - Use Implicit Waits for basic sync (applies globally to findElement).
     *    - Use Explicit Waits (WebDriverWait) to wait for specific conditions (like visibility, clickability).
     *
     *    Example:
     *    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     *    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dynamic-input")));
     *
     * This ensures the page and elements are *ready* before interacting — reducing test flakiness.
     */

    /**
     * 🕣 IMPLICIT WAIT
     * Demonstrates the use of an implicit wait.
     * Implicit wait tells Selenium to wait up to the defined duration
     * when trying to locate any element before throwing a NoSuchElementException.
     *
     * ⚠️ WARNING: Do not mix implicit and explicit waits in the same test.
     */
    public static void demonstrateImplicitWait() {
        WebDriver driver = new ChromeDriver();

        // ✅ Set global implicit wait — applies to all findElement() calls
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");

        // ✅ Click to reveal dynamic input
        driver.findElement(By.id("reveal")).click();

        // ✅ Now locate the newly revealed input (waits up to 5s due to implicit wait)
        WebElement dynamicInput = driver.findElement(By.id("revealed"));
        dynamicInput.sendKeys("Hello, Selenium!");

        driver.quit();
    }
    /**
     * 🕣 EXPLICIT WAIT
     * Demonstrates an explicit wait using WebDriverWait.
     *
     * Unlike implicit waits, explicit waits target specific conditions —
     * like visibility, presence, clickability — and only wait when needed.
     *
     * ✅ Avoids over-waiting
     * ✅ Targets conditions precisely
     * ✅ Recommended for dynamic or slow-loading elements
     *
     * ⚠️ WARNING: Do not combine with implicit waits — this can lead to
     * unexpected delays or timeouts.
     */
    public static void demonstrateExplicitWait() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");

        // ✅ Click the button that reveals a hidden input
        driver.findElement(By.id("reveal")).click();

        // ✅ Explicit wait: wait until the input is visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement revealedInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("revealed"))
        );



        // ✅ Safe to interact now
        revealedInput.sendKeys("Explicit wait succeeded!");

        driver.quit();
    }

    /**
     *  🕣 FLUENT WAIT
     * Demonstrates how to customize an explicit wait with:
     * - Custom polling interval
     * - Ignored exceptions (e.g., ElementNotInteractableException)
     * - Custom timeout message
     * - Fluent lambda condition
     */

    public static void demonstrateCustomExplicitWait() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");

        // Click to reveal the dynamic input
        driver.findElement(By.id("reveal")).click();

        // ✅ Create a FluentWait with:
        //    - 10s timeout
        //    - 500ms polling
        //    - Ignoring ElementNotInteractableException
        //    - Custom message
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(ElementNotInteractableException.class)
                .ignoring(NoSuchElementException.class)
                .withMessage("Custom timeout: Element not ready for interaction within 10 seconds.");

        // ✅ Lambda condition — returns true only when element is displayed AND enabled
        WebElement revealedInput = wait.until(driverInstance -> {
            WebElement input = driverInstance.findElement(By.id("revealed"));
            if (input.isDisplayed() && input.isEnabled()) {
                input.sendKeys("Customized wait succeeded!");
                return input; // returning the usable element
            }
            return null;
        });

        driver.quit();
    }

}
