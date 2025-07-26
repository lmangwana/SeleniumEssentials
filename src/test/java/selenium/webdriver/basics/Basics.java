package selenium.webdriver.basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class Basics {


    @Test
    public void demoSeleniumBasics() {
        // 1. Start the session
        WebDriver driver = new ChromeDriver();

        // 2. Take action on browser (navigate to URL)
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        // 3. Request browser information
        String title = driver.getTitle();
        System.out.println("Page Title: " + title);

        // 4. Establish Waiting Strategy (implicit wait for demo purposes)
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

        // 5. Find an element
        WebElement textBox = driver.findElement(By.name("my-text"));
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

        // 6. Take action on element
        textBox.sendKeys("Selenium");
        submitButton.click();

        // 7. Request element information
        WebElement message = driver.findElement(By.id("message"));
        System.out.println("Message Displayed: " + message.getText());

        // 8. End the session
        driver.quit();
    }
}
