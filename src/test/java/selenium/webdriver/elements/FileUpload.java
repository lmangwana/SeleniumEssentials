package selenium.webdriver.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class FileUpload {

    /**
     * 📂 File Upload with Selenium
     *
     * Selenium cannot interact with the native file picker dialog,
     * so it bypasses it by directly sending the file path to an <input type="file"> element.
     *
     * ✅ Steps:
     *  1. Locate the file input element
     *  2. Use .sendKeys() with the absolute file path
     *  3. Click the submit or upload button
     *
     * ⚠️ Notes:
     * - Only works on elements of type="file"
     * - The file must exist on the local machine running the test
     * - For remote WebDriver sessions, use a LocalFileDetector
     */
    public static void demonstrateFileUpload() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/upload"); // Example upload test site

        // ✅ 1. Locate the file input
        WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']"));

        // ✅ 2. Specify the file to upload
        File uploadFile = new File("src/test/resources/testdata/sample_upload.txt");
        fileInput.sendKeys(uploadFile.getAbsolutePath()); // getPath() or .toString() is enough if pathname is absolute

        // ✅ 3. Click the upload button
        driver.findElement(By.id("file-submit")).click();

        // ✅ 4. Optional: Verify successful upload
        String confirmationText = driver.findElement(By.id("uploaded-files")).getText();
        System.out.println("Uploaded File: " + confirmationText);

        driver.quit();
    }
}
