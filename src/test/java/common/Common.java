package common;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Common {

    private static WebDriver driver;
    //Selenium owner method

    private static final int TIME_OUT_IN_SECONDS = 60;
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
        wait = new WebDriverWait(getDriver(), TIME_OUT_IN_SECONDS);
    }

    public static void visit(String url) {
        driver.get(url);
    }

    public static void addLocalStorageToken(String name, String value) {
        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        localStorage.setItem(name, value);
    }

    public static void reload(String url){
        driver.get(url);
        driver.navigate().refresh();
    }

    public static void click(By by) {
        driver.findElement(by).click();
    }

    public static String getText(By by) {
        return wait
                .until(
                        ExpectedConditions
                                .visibilityOfElementLocated(by)
                )
                .getText();
    }

}
