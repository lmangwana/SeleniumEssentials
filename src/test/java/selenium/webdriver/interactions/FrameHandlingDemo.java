package selenium.webdriver.interactions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FrameHandlingDemo {

    /**
     * 🖼️ Working with Frames and IFrames in Selenium
     *
     * Frames (<frame>) and IFrames (<iframe>) are HTML documents embedded inside another HTML document.
     * To interact with elements inside a frame, Selenium must first switch context into that frame.
     *
     * ✅ Common actions:
     *  1. Switch to a frame by name, index, or WebElement
     *  2. Interact with elements inside the frame
     *  3. Switch back to the main (default) content
     *  4. Switch to parent frame (in nested frames)
     */
    public static void demonstrateFrameHandling() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/iframe"); // Example iframe page

        // 1️⃣ Switch to the <iframe> using its ID/name
        driver.switchTo().frame("mce_0_ifr");
        // OR     //switch To IFrame using Web Element
        //         WebElement iframe = driver.findElement(By.id("iframe1"));
        //         //Switch to the frame
        //         driver.switchTo().frame(iframe);

        // 2️⃣ Interact with an element inside the iframe
        WebElement textBox = driver.findElement(By.id("tinymce"));
        textBox.clear();
        textBox.sendKeys("Selenium inside an iframe!");

        // 3️⃣ Switch back to the main page (default content)
        driver.switchTo().defaultContent();

        // ✅ Example of a page element outside the iframe
        WebElement heading = driver.findElement(By.tagName("h3"));
        System.out.println("Main page heading: " + heading.getText());

        driver.quit();
    }
}
