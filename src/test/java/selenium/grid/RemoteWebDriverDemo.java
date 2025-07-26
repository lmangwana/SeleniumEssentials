package selenium.grid;

/**
 * RemoteWebDriverDemo.java
 *
 * Demonstrates:
 * - Local execution using ChromeDriver
 * - Remote execution using RemoteWebDriver
 * - Explanation of Selenium Grid (Hub and Node setup)
 * - Code snippets and comments for practical understanding
 */

package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class RemoteWebDriverDemo {

    public static void main(String[] args) throws MalformedURLException {

        // ===========================
        // ✅ 1. LOCAL EXECUTION
        // ===========================
        WebDriver localDriver = new ChromeDriver(); // Launches Chrome locally
        localDriver.get("https://example.com");
        System.out.println("Local title: " + localDriver.getTitle());
        localDriver.quit();

        // ===========================
        // 🔁 2. REMOTE EXECUTION (Selenium Grid)
        // ===========================

        // Define browser options (can be FirefoxOptions, EdgeOptions, etc.)
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        // URL of the remote Grid Hub — can be localhost or a different machine's IP
        URL gridURL = new URL("http://192.168.1.10:4444/wd/hub"); // Replace with your Grid IP

        // RemoteWebDriver communicates with the Selenium Grid Hub to run the test
        WebDriver remoteDriver = new RemoteWebDriver(gridURL, options);
        remoteDriver.get("https://example.com");
        System.out.println("Remote title: " + remoteDriver.getTitle());
        remoteDriver.quit();
    }

    // =====================================================
    // 🔧 What is Selenium Grid?
    // =====================================================
    // Selenium Grid allows you to:
    // - Run tests on different machines (Nodes) from a central server (Hub)
    // - Execute tests in parallel across browsers, OSes, and devices
    // - Scale test execution without needing local resources

    // 🧱 Components:
    // - Hub: Receives tests and routes them to appropriate nodes
    // - Node: Executes the test with the requested browser/environment

    // =====================================================
    // 🚀 How to Set Up Grid Locally (Basic)
    // =====================================================
    // 1. Download Selenium Server jar (Grid version)
    // 2. Start Hub:
    //      java -jar selenium-server.jar hub
    // 3. Start Node and register it to the hub:
    //      java -jar selenium-server.jar node --hub http://localhost:4444
    // 4. Then run this Java program using RemoteWebDriver to connect to the Grid

    // 💡 Tip: Use Docker Selenium Grid for quick setup: https://github.com/SeleniumHQ/docker-selenium

    // =====================================================
    // ✅ Summary
    // =====================================================
    // - Use ChromeDriver for local tests
    // - Use RemoteWebDriver(URL, options) to run tests on Grid/cloud machines
    // - Grid enables parallel and distributed test execution
}
