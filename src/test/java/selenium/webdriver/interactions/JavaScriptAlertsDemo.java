package selenium.webdriver.interactions;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JavaScriptAlertsDemo {

    /**
     * 📢 Handling JavaScript Alerts, Confirms, and Prompts
     *
     * JavaScript provides three types of popups in the browser:
     * 1. Alerts   — Basic message with OK
     * 2. Confirms — Message with OK and Cancel
     * 3. Prompts  — Message with text input + OK/Cancel
     *
     * WebDriver can:
     * ✅ Switch to the popup via driver.switchTo().alert()
     * ✅ Read the message with alert.getText()
     * ✅ Accept (OK) with alert.accept()
     * ✅ Dismiss (Cancel) with alert.dismiss()
     * ✅ Send input (for prompts) with alert.sendKeys("your input")
     */
    public static void handleJavaScriptPopups() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // 1️⃣ Handle Alert (OK only)
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert text: " + alert.getText());
        alert.accept();

        // 2️⃣ Handle Confirm (OK or Cancel)
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert confirm = driver.switchTo().alert();
        System.out.println("Confirm text: " + confirm.getText());
        confirm.dismiss(); // or confirm.accept();

        // 3️⃣ Handle Prompt (text input + OK/Cancel)
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert prompt = driver.switchTo().alert();
        System.out.println("Prompt text: " + prompt.getText());
        prompt.sendKeys("Selenium"); // replace default text
        prompt.accept();

        driver.quit();
    }
}