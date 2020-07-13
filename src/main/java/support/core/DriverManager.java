package support.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import model.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class DriverManager {

    public static DriverManager instance;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();

    private DriverManager() {

    }

    public static DriverManager getInstance() {
        if (instance == null)
            instance = new DriverManager();

        return instance;
    }

    public void startDriver(BrowserType browserType) {
        if(browserType == BrowserType.CHROME){
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());
        }
        if(browserType == BrowserType.FIREFOX){
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());
        }
        if(browserType == BrowserType.IE){
            WebDriverManager.iedriver().setup();
            driver.set(new InternetExplorerDriver());
        }

        setDefaultTimeOut();
    }

    public void stopDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    public WebDriver getDriver() {
        if (driver.get() == null) {
            startDriver(BrowserType.valueOf(PropertieManager.getInstance().readProperties().getProperty("browserName")));
        }

        return driver.get();
    }

    public void setDefaultTimeOut() {
        wait.set(new WebDriverWait(driver.get(), 10));
        driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
