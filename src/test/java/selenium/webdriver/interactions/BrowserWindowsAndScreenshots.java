package selenium.webdriver.interactions;

import org.openqa.selenium.Pdf;
import org.openqa.selenium.PrintsPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.print.*;
import org.openqa.selenium.print.PrintOptions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import java.util.Set;

public class BrowserWindowsAndScreenshots {

    public static void main(String[] args) throws IOException {
        WebDriver driver = new ChromeDriver();

        // ----------------- Open a URL -----------------
        driver.get("https://www.selenium.dev/selenium/web/window_switching_tests/page_with_frame.html");

        // ----------------- Get Current Window Handle -----------------
        String currentWindow = driver.getWindowHandle();
        System.out.println("Current Window Handle: " + currentWindow);

        // ----------------- Open new tab and switch -----------------
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.example.com");
        System.out.println("New Tab Title: " + driver.getTitle());

        // ----------------- Switch back to original window -----------------
        driver.switchTo().window(currentWindow);

        // ----------------- Open new window and switch -----------------
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://www.selenium.dev");
        System.out.println("New Window Title: " + driver.getTitle());

        // ----------------- Close current window -----------------
        driver.close();

        // ----------------- Switch back to original window again -----------------
        driver.switchTo().window(currentWindow);

        // ----------------- Resize browser window -----------------
        driver.manage().window().setSize(new Dimension(1024, 768));

        // ----------------- Move window to (0,0) -----------------
        driver.manage().window().setPosition(new Point(0, 0));

        // ----------------- Maximize, Minimize, and Fullscreen -----------------
        driver.manage().window().maximize();
        driver.manage().window().minimize();
        driver.manage().window().fullscreen();

        // ----------------- Take Screenshot of Page -----------------
        File fullScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(fullScreenshot, new File("./pageScreenshot.png"));

        // ----------------- Take Screenshot of a WebElement -----------------
        WebElement logo = driver.findElement(By.cssSelector("img[alt='Selenium Logo']"));
        File elementScreenshot = logo.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(elementScreenshot, new File("./elementScreenshot.png"));

        // ----------------- Execute JavaScript -----------------
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement searchBox = driver.findElement(By.name("search"));
        js.executeScript("arguments[0].value='WebDriver';", searchBox);
        String innerText = (String) js.executeScript("return arguments[0].innerText;", searchBox);
        System.out.println("Inner Text: " + innerText);
        js.executeScript("console.log('hello world from Selenium!');");

        // ----------------- Print the current page (Headless Chrome required) -----------------
        if (driver instanceof PrintsPage) {
            PrintOptions printOptions = new PrintOptions();
            printOptions.setPageRanges("1-2");
            Pdf pdf = ((PrintsPage) driver).print(printOptions);
            String pdfContent = pdf.getContent(); // Base64-encoded PDF content
            System.out.println("PDF Length: " + pdfContent.length());
        }

        // ----------------- Clean up -----------------
        driver.quit(); // Closes all browser windows and ends session
    }
}