package selenium.webdriver.actionsapi;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;

import java.time.Duration;

public class ScrollActionsDemo {
    public static void main(String[] args) throws InterruptedException {

        // 1️⃣ Setup WebDriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Navigate to a scrollable page
        driver.get("https://demoqa.com/nestedframes");

        // 2️⃣ Scenario 1: Scroll to Element (scrolls to bottom of iframe)
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        new Actions(driver)
                .scrollToElement(iframe)
                .perform();
        Thread.sleep(1500);

        // 3️⃣ Scenario 2: Scroll by given amount (scrolls vertically to footer)
        WebElement footer = driver.findElement(By.tagName("footer"));
        int deltaY = footer.getRect().y;
        new Actions(driver)
                .scrollByAmount(0, deltaY)
                .perform();
        Thread.sleep(1500);

        // 4️⃣ Scenario 3: Scroll from an element by a given amount
        WheelInput.ScrollOrigin scrollOriginFromElement = WheelInput.ScrollOrigin.fromElement(iframe);
        new Actions(driver)
                .scrollFromOrigin(scrollOriginFromElement, 0, 200)
                .perform();
        Thread.sleep(1500);

        // 5️⃣ Scenario 4: Scroll from an element with an offset
        WheelInput.ScrollOrigin offsetFromFooter = WheelInput.ScrollOrigin.fromElement(footer, 0, -50);
        new Actions(driver)
                .scrollFromOrigin(offsetFromFooter, 0, 200)
                .perform();
        Thread.sleep(1500);

        // 6️⃣ Scenario 5: Scroll from a viewport offset
        WheelInput.ScrollOrigin offsetFromViewport = WheelInput.ScrollOrigin.fromViewport(10, 10);
        new Actions(driver)
                .scrollFromOrigin(offsetFromViewport, 0, 200)
                .perform();
        Thread.sleep(1500);

        // Close browser
        driver.quit();
    }
}
