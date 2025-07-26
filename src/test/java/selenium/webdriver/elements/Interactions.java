package selenium.webdriver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Interactions {

    /**
     * 🎯 Interacting with Web Elements in Selenium
     *
     * Selenium provides 5 primary actions for manipulating form and content elements:
     *
     * 1. click()     — Any clickable element (buttons, links, checkboxes, etc.)
     * 2. sendKeys()  — Type into inputs, textareas, or contenteditable elements
     * 3. clear()     — Clear text fields before typing new input
     * 4. submit()    — Submit a form (on form elements or inputs inside forms)
     * 5. select      — Use Select class for dropdowns (single/multi-select)
     */

    public static void interactWithElements() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        // 1️⃣ click() — Click any element (e.g., checkbox)
        WebElement checkbox = driver.findElement(By.id("my-check-1"));
        checkbox.click();

        // 2️⃣ sendKeys() — Type text into an input field
        WebElement textInput = driver.findElement(By.name("my-text"));
        textInput.sendKeys("Lutho's Test Input");

        // 3️⃣ clear() — Erase existing input before sending new keys
        textInput.clear();
        textInput.sendKeys("Updated Input");

        // 4️⃣ submit() — Submit the form via an input inside the <form>
        WebElement formInput = driver.findElement(By.name("my-password"));
        formInput.sendKeys("123456");
        formInput.submit(); // Submits the parent form

        // 5️⃣ select — Select dropdown option using Select class
        WebElement dropdown = driver.findElement(By.name("my-select"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Two"); // Can also use selectByValue("2")

        driver.quit();
    }
}
