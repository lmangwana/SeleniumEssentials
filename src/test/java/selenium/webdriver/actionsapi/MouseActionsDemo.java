package selenium.webdriver.actionsapi;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.time.Duration;

public class MouseActionsDemo {

    @Test
    public static void mouseActions()throws InterruptedException {

        // Set up WebDriver (Chrome in this case)
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/buttons"); // Demo site with mouse interactions

        // Create Actions object for mouse events
        Actions actions = new Actions(driver);

        // 1️⃣ Hover over an element (used for dropdowns, tooltips, etc.)
        WebElement hoverTarget = driver.findElement(By.cssSelector("#doubleClickBtn"));
        actions.moveToElement(hoverTarget).perform();

        Thread.sleep(2000);
        // 2️⃣ Right-click (context click)
        WebElement rightClickButton = driver.findElement(By.id("rightClickBtn"));
        actions.contextClick(rightClickButton).perform();

        Thread.sleep(2000);
        // 3️⃣ Double-click
        WebElement doubleClickButton = driver.findElement(By.id("doubleClickBtn"));
        actions.doubleClick(doubleClickButton).perform();

        Thread.sleep(2000);
        // 4️⃣ Single Click using Actions (alternative to element.click())
        WebElement singleClickButton = driver.findElement(By.xpath("//button[text()='Click Me']"));
        actions.click(singleClickButton).perform();

        Thread.sleep(2000);
        // 🔁 5️⃣ Click-and-hold and release (can be used for selection or drag-start)
        WebElement holdBox = driver.findElement(By.cssSelector("div[class='element-list collapse show'] li[id='item-1'] span[class='text']")); // Assume this is a draggable box
        actions.clickAndHold(holdBox).pause(1000).release().perform();

        // 6️⃣ Drag and Drop (requires source and target elements)
        driver.get("https://demoqa.com/droppable"); // New page with drag-drop example
        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));
        actions.dragAndDrop(source, target).perform();

        Thread.sleep(2000);
        // 7️⃣ Drag and drop using clickAndHold → moveToElement → release
        driver.get("https://demoqa.com/droppable");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement source2 = driver.findElement(By.id("draggable"));
        WebElement target2 = driver.findElement(By.id("droppable"));
        actions.clickAndHold(source2).moveToElement(target2).release().build().perform();

        Thread.sleep(2000);
        // Close browser
        driver.quit();
    }
}
