package common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class Common {

    private static WebDriver driver;
    //Selenium owner method

    private static final int TIME_OUT_IN_SECONDS = 90;
    public static WebDriverWait wait;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void openBrowser(String name) {
        switch (name.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "ie":
                driver = new InternetExplorerDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("The browser " + name + "does not support");
        }
        driver.manage().window().maximize();
        wait = new WebDriverWait(getDriver(), TIME_OUT_IN_SECONDS);
    }

    public static void visit(String url) {
        driver.get(url);
    }

    public static void refreshPage() throws InterruptedException {
        driver.navigate().refresh();
    }

    public static void close() {
        if (driver != null) {
            driver.quit();
        }
    }
    public static void acceptAlert() {
        driver.switchTo().alert().accept();
    }
    public static void addLocalStorageToken(String name, String value) {
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        localStorage.setItem(name, value);
    }

    public static void click(By by) {
        driver.findElement(by).click();
    }

    public static void fill(By by, String withText) {
//        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(withText);
    }
    public static String getText(How how, String locator) {
        return wait
                .until(
                        ExpectedConditions
                                .visibilityOfElementLocated(how.buildBy(locator))
                )
                .getText();
    }
    public static String getText(By by) {
        return wait
                .until(
                        ExpectedConditions
                                .visibilityOfElementLocated(by)
                )
                .getText();
    }

    public static WebElement find(By by){
        return wait
                .until(
                        ExpectedConditions
                                .visibilityOfElementLocated(by)
                ).findElement(by);
    }

    public static void hover(By by){
        Actions builder = new Actions(driver);
        WebElement webElement = find(by);
        builder.moveToElement(webElement).perform();
    }

    public static void captureScreenshot(String fileName) {
        File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenShot, new File("./target/screenshot-" + fileName + "-" + System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


