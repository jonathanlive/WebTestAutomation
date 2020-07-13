package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import support.Exception.PageException;
import support.actions.Actions;
import support.core.DriverManager;

public class BasePage {

    public static WebDriver driver;
    public static Actions actions;

    public static <T extends BasePage> T getInstance(Class<T> clazz) {
        try {
            driver = DriverManager.getInstance().getDriver();
            actions = new Actions();
            T pageObject = clazz.getDeclaredConstructor().newInstance();
            PageFactory.initElements(DriverManager.getInstance().getDriver(), pageObject);
            return pageObject;
        } catch (Exception e) {
            throw new PageException(e.getMessage());
        }
    }

}

