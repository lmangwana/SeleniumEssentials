# Selenium Essentials Portfolio 🚀

This repository is a structured documentation and experimentation space for the Selenium WebDriver libraries. It includes practical examples, documentation references, and test cases structured around real-world scenarios — all implemented using Java, TestNG, and Maven.

---

## 📌 What This Project Covers

- ✅ WebDriver initialization & browser automation
- ✅ Element location strategies (ID, CSS, XPath)
- ✅ WebElement actions (click, sendKeys, getText, etc.)
- ✅ Waits (implicit, explicit with WebDriverWait)
- ✅ Browser navigation & control
- ✅ Remote execution using RemoteWebDriver & Grid
- ✅ WebDriver event listeners & logging
- ✅ Advanced interactions (Actions class, JS Executor)
- ✅ Exception handling, screenshots, and debug logs

---

## 📁 Project Structure

| Folder | Purpose |
|--------|---------|
| `src/main/java/selenium/webdriver/...` | Structured by WebDriver topics (e.g., basics, waits, actionsapi) |
| `src/main/java/selenium/grid/` | RemoteWebDriver and Selenium Grid examples |
| `src/main/java/selenium/testpractices/` | Experimental patterns and design best practices |
| `src/test/java/` | Test classes for running and validating logic |
| `README.md` | Project summary, setup, and usage instructions |

---

## 💡 How to Run

### ✅ Prerequisites:
- Java 17+
- Maven installed
- IntelliJ IDEA (or any Java IDE)
- Google Chrome, Edge, Safari

---

### ▶️ Running Tests

**Option 1: From IntelliJ**
- Navigate to any test class
- Right-click → `Run 'ClassName'`

**Option 2: From Terminal**
```bash
mvn clean test