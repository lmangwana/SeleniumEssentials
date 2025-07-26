package selenium.webdriver.actionsapi;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

/**
 * 🔤 Keyboard Actions in Selenium WebDriver
 * -----------------------------------------
 * Keyboard actions simulate key presses using the Actions class.
 * Selenium supports pressing, holding, and releasing keyboard keys
 * using the `Actions` API and the `Keys` enum.
 *
 * 🎯 Use Cases:
 * - Typing text with key modifiers (e.g., SHIFT for uppercase)
 * - Navigating or manipulating input fields via keyboard
 * - Copy/paste or shortcut simulations
 *
 * 🧰 Common Methods:
 * - keyDown(Keys.KEY): Hold a key
 * - keyUp(Keys.KEY): Release the key
 * - sendKeys(String or WebElement): Type characters
 *
 * 🧠 Tip: For Mac users, use COMMAND instead of CONTROL for copy/paste
 *A
 * ✅ Example 1: Hold SHIFT and type 'A'
 */
public class KeyboardActionsExample {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://example.com");

        // Example 1: Holding SHIFT to type uppercase 'A'
        new Actions(driver)
                .keyDown(Keys.SHIFT)
                .sendKeys("a") // Will be typed as 'A'
                .keyUp(Keys.SHIFT)
                .perform();

        // Example 2: Type 'a' with SHIFT down, then 'b' after releasing SHIFT
        new Actions(driver)
                .keyDown(Keys.SHIFT)
                .sendKeys("a") // SHIFT + a => 'A'
                .keyUp(Keys.SHIFT)
                .sendKeys("b") // lowercase b
                .perform();

        // Example 3: Send keys directly (focus on active element)
        new Actions(driver)
                .sendKeys("Hello World!")
                .perform();

        // Example 4: Send keys to a specific element
        WebElement input = driver.findElement(By.id("textInput"));
        new Actions(driver)
                .sendKeys(input, "Selenium!")
                .perform();

        // Example 5: Copy and paste using Ctrl/Command + X/V
        Keys cmdCtrl = Platform.getCurrent().is(Platform.MAC) ? Keys.COMMAND : Keys.CONTROL;

        new Actions(driver)
                .sendKeys(input, "Selenium!")
                .sendKeys(Keys.ARROW_LEFT) // Move cursor left, before "!"
                .keyDown(Keys.SHIFT)
                .sendKeys(Keys.ARROW_UP)   // Select text
                .keyUp(Keys.SHIFT)
                .keyDown(cmdCtrl)
                .sendKeys("xvv")           // Cut, then paste twice
                .keyUp(cmdCtrl)
                .perform();
        /*
            keyDown(cmdCtrl) + sendKeys("xvv") - since cmdCtrl is still pressed down
            •	x = Cut selected Selenium // cmdCtrl + x
            •	v = Paste Selenium (1st time) // cmdCtrl + v
            •	v = Paste Selenium again (2nd time) // cmdCtrl + v
         */

        // ✅ Expected Result: "SeleniumSelenium!"
        String result = input.getAttribute("value");
        System.out.println("Final input value: " + result);

        driver.quit();
    }

    @Test
    public static void completeForm() throws InterruptedException {
        /*
        ✅ Goal Recap
            1.	Type "Luke" into Full Name
            2.	Copy and paste that into Email using keyboard actions
            3.	Append "@G" via:
            •	@ → Shift + 2 (on Mac)
            •	G → Shift + g → results in capital G
         */

        // Selectors
         By fullnameInput = By.id("fullname");
         By emailInput = By.id("email");
         By addressInput = By.id("address");
         By passwordInput = By.id("password");
         By submitButton = By.cssSelector("input[type='submit']");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.tutorialspoint.com/selenium/practice/text-box.php");

        // Send name to Full Name input
        driver.findElement(fullnameInput).sendKeys("Luke");
        new Actions(driver)
                .click(driver.findElement(fullnameInput))               // focus on the field
                .keyDown(Keys.COMMAND).sendKeys("a").sendKeys("c").keyUp(Keys.COMMAND)  // CMD + A + C
                .sendKeys(Keys.TAB)                                        // Tab to email field
                .keyDown(Keys.COMMAND).sendKeys("v").keyUp(Keys.COMMAND)               // CMD + V
                // Append @ using SHIFT+2
                .keyDown(Keys.SHIFT).sendKeys("2").keyUp(Keys.SHIFT)
                // Append capital G using SHIFT+g
                .keyDown(Keys.SHIFT).sendKeys("g").keyUp(Keys.SHIFT)
                .perform();
        Thread.sleep(2000);
        driver.quit();
    }
}
