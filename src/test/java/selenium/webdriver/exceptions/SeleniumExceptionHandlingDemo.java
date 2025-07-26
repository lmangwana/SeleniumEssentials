package selenium.webdriver.exceptions;
import com.mongodb.connection.SslSettings;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * SeleniumExceptionHandling.java
 *
 * This class demonstrates:
 * — Real-world Selenium exceptions
 * — When and why they occur
 * — How to handle them using try-catch, throws, and custom utilities
 */
public class SeleniumExceptionHandlingDemo {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        /*
            ⚠️ Use try and multiple catch blocks
            Some pieces of code can produce multiple exceptions.
            Each catch block is used to handle a single type of exception.
         */
        try {
            driver.get("https://www.tutorialspoint.com/selenium/practice/auto-complete.php");

            // ❌ This will throw NoSuchElementException because the ID might be incorrect or element not loaded yet
            WebElement input = driver.findElement(By.xpath("//*[@id='tag']")); // try changing to "//*[@id='tags']"
            input.sendKeys("Selenium");

        } catch (NoSuchElementException e) {
            // Happens when driver can't find an element in the DOM (wrong locator, element not yet rendered)
            System.out.println("❌ Element not found. Possibly wrong locator or page not loaded.");
            e.printStackTrace();

        } catch (ElementNotInteractableException e) {
            // Happens when element exists in DOM but can't be interacted with (e.g., hidden, disabled)
            System.out.println("❌ Element found but not interactable. It might be hidden or disabled.");

        } catch (TimeoutException e) {
            // Happens when expected condition (like presence of element) isn’t met in given wait time
            System.out.println("❌ Timeout: Element didn’t appear in time. Consider using WebDriverWait.");

        } catch (StaleElementReferenceException e) {
            // Happens when the DOM has changed after finding an element (e.g., after page reload or AJAX update)
            System.out.println("❌ Element reference lost due to DOM update. Refetch the element before using it.");

        } finally {
            // This block always runs — good for clean-up (closing browser)
            System.out.println("✅ Test finished. Closing browser.");
            driver.quit();
        }
    }

    /**
     * Simulates a scenario where a custom exception is thrown deliberately.
     *
     * 🔹 The 'throw' keyword is used to manually create and trigger an exception when a specific condition is met.
     * 🔹 The 'throws' keyword in the method signature indicates that this method might throw an exception,
     *     and the calling method must handle it (e.g., with try-catch or propagate it further).
     *
     * In this case:
     * — If the WebElement is not enabled (i.e., cannot be interacted with), we throw an InvalidElementStateException
     *   with a custom message to fail the test gracefully.
     */
    public static void throwExample(WebElement element) throws InvalidElementStateException {
        if (!element.isEnabled()) {
            // ❗ Stop the test and throw a meaningful error
            throw new InvalidElementStateException("Username input field is not editable. Test cannot proceed.");
        }
    }

    /**
     * Cheatsheet: When they happen and how to fix them
     */
    public static void listSeleniumExceptions() {
        /*
         * 🔍 NoSuchElementException:
         * Happens: Element not found using given locator.
         * Fix: Check locator, wait for visibility, use WebDriverWait.

         * 👻 ElementNotVisibleException (deprecated):
         * Happens: Element exists in DOM but is not visible.
         * Fix: Use waits or scroll into view.

         * 🚫 ElementNotInteractableException:
         * Happens: Element is not ready for interaction (e.g., disabled, obscured).
         * Fix: Wait or adjust app state (e.g., close modal).

         * ⛔ ElementClickInterceptedException:
         * Happens: Click blocked by overlay (modal, ad).
         * Fix: Wait for overlay to disappear or scroll element into view.

         * 📍 InvalidSelectorException:
         * Happens: Invalid XPath/CSS used.
         * Fix: Correct your selector format.

         * ⌛ TimeoutException:
         * Happens: Element did not appear within wait time.
         * Fix: Increase wait time or use better conditions.

         * 🔁 StaleElementReferenceException:
         * Happens: Element is no longer in DOM.
         * Fix: Re-fetch the element after reload or DOM change.

         * 🌐 WebDriverException:
         * Generic browser interaction failure. Could be browser crash or driver issues.

         * 🖼️ NoSuchFrameException / NoSuchWindowException:
         * Happens: Trying to switch to a window/frame that doesn’t exist.
         * Fix: Ensure the frame/window is opened first.

         * 🚨 UnexpectedAlertPresentException:
         * Happens: Alert shows up and is not handled.
         * Fix: Accept/dismiss the alert using driver.switchTo().alert().

         * 🧼 SessionNotFoundException:
         * Happens: Browser was closed and script tried to interact with it.
         * Fix: Ensure session is still valid before interacting.

         * ❗ InvalidArgumentException:
         * Happens: You passed an invalid value to WebDriver (e.g., invalid timeout).
         * Fix: Check method parameters.

         * 🍪 InvalidCookieDomainException:
         * Happens: Adding cookie to wrong domain.
         * Fix: Ensure URL domain matches cookie domain.

         * 🚷 MoveTargetOutOfBoundsException:
         * Happens: Trying to drag/move to a point outside viewport.
         * Fix: Scroll to element or validate coordinates.
         */
    }

    /**
     * A generic exception handler for reusability in frameworks
     */
    public static void handleWithMessage(Runnable step, String stepName) {
        try {
            step.run();
        } catch (Exception e) {
            System.out.println("⚠️ Exception at step: " + stepName);
            System.out.println("Type: " + e.getClass().getSimpleName());
            System.out.println("Message: " + e.getMessage());
        }
    }
}