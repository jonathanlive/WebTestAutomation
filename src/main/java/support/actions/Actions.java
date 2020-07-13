package support.actions;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.core.DriverManager;
import java.util.List;

public class Actions {

    private static WebDriverWait wait = new WebDriverWait(DriverManager.getInstance().getDriver(), 10);
    private static WebDriver driver = null;
    private static JavascriptExecutor js = null;

    public Actions(){
        this.driver = DriverManager.getInstance().getDriver();
        js = (JavascriptExecutor) driver;
    }

    public void waitForSeconds(int timeWaitInSeconds) {
        try {
            Thread.sleep(timeWaitInSeconds * 1000);
        } catch (InterruptedException e) {
            throw new support.Exception.AutomationException(e.getMessage());
        }
    }

    public WebElement waitForElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return element;
        } catch (StaleElementReferenceException e1) {
            try {
                wait.until(ExpectedConditions.visibilityOf(element));
                wait.until(ExpectedConditions.elementToBeClickable(element));
                return element;
            } catch (Exception e2) {
                System.out.println("Erro ao capturar elemento: " + e2.getMessage());
            }
        } catch (Exception e3) {
            System.out.println("Erro ao capturar elemento: " + e3.getMessage());
        }

        return null;
    }

    public WebElement waitForElement(By by) {
        try {
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
            wait.until(ExpectedConditions.elementToBeClickable(by));
            return driver.findElement(by);
        } catch (Exception e) {
            System.out.println("Erro ao capturar elemento: " + e.getMessage());
        }

        return null;
    }

    public List<WebElement> waitForElements(By by) {
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
            return driver.findElements(by);
        } catch (StaleElementReferenceException e1) {
            try {
                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
                return driver.findElements(by);
            } catch (Exception e2) {
                System.out.println("Erro ao capturar os elementos: " + e2.getMessage());
            }
        } catch (Exception e3) {
            System.out.println("Erro ao capturar os elementos: " + e3.getMessage());
        }

        return null;
    }

    public void selectDropDownMenuByValue(WebElement element,String value){
        waitForElement(element);
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }

    public  void click(WebElement element) {
        if (waitForElement(element) != null)
            element.click();
    }

    public void click(By by) {
        WebElement element = waitForElement(by);
        if (waitForElement(element) != null)
            element.click();
    }

    public void scrollDownUntilIsVisible(WebElement element) {
        try {
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception ex) {
            throw new support.Exception.AutomationException(ex.getMessage());
        }
    }

    public void highlightElement(WebElement element) {
        try {
            js.executeScript("arguments[0].style.border='2px dotted red'", element);
        } catch (Exception ex) {
            throw new support.Exception.AutomationException(ex.getMessage());
        }
    }

    public String executeScript(String script) {
        try {
            return (String) js.executeScript(script);
        } catch (Exception ex) {
            throw new support.Exception.AutomationException(ex.getMessage());
        }
    }
}
