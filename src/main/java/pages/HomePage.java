package pages;

import model.Car;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import support.core.ExecutionManager;
import support.util.StringUtils;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(how = How.CSS, using = "#buscaForm > div:nth-child(5) > div > div > div > div > div > div > div > input")
    private List<WebElement> aboveInput;

    @FindBy(css = "#buscaForm > div:nth-child(6) > div > div > div > div > div > div > div > input")
    private List<WebElement> bottomInput;

    @FindBy(how = How.CSS, using = "#buscaForm > div:nth-child(5) > div > div > div > div > div > button")
    private List<WebElement> aboveButton;

    @FindBy(css = "#buscaForm > div:nth-child(6) > div > div > div > div > div > button")
    private List<WebElement> bottomButton;

    @FindBy(css = "#buscaForm > div:nth-child(7) > div.col-xs-4.p-top-xs > button")
    private WebElement btnSearch;

    public void selectBrand(String brand) {
        boolean elementIsVisible = false;
        actions.waitForSeconds(3);
        aboveButton.get(0).click();
        aboveInput.get(0).sendKeys(brand);
        for (WebElement element : actions.waitForElements(By.tagName("a"))) {
            if (element.getText().equalsIgnoreCase(brand)) {
                element.click();
                elementIsVisible = true;
                break;
            }
        }

        if (!elementIsVisible)
            Assert.fail("Brand not found: " + brand);
    }

    public void selectModel(String model) {
        boolean elementIsVisible = false;
        aboveInput.get(1).sendKeys(model);
        for (WebElement element : actions.waitForElements(By.tagName("a"))) {
            if (element.getText().equalsIgnoreCase(model)) {
                element.click();
                elementIsVisible = true;
                break;
            }
        }

        if (!elementIsVisible)
            Assert.fail("Model not found: " + model);
    }

    public void selectMinYear(String minYear) {
        boolean elementIsVisible = false;
        bottomButton.get(0).click();
        bottomInput.get(0).sendKeys(minYear);
        for (WebElement element : actions.waitForElements(By.tagName("a"))) {
            if (element.getText().equalsIgnoreCase(minYear)) {
                element.click();
                elementIsVisible = true;
                break;
            }
        }

        if (!elementIsVisible)
            Assert.fail("minYear not found: " + minYear);
    }

    public void selectMaxYear(String maxYear) {
        boolean elementIsVisible = false;
        bottomButton.get(1).click();
        bottomInput.get(1).sendKeys(maxYear);
        for (WebElement element : actions.waitForElements(By.tagName("a"))) {
            if (element.getText().equalsIgnoreCase(maxYear)) {
                element.click();
                elementIsVisible = true;
                break;
            }
        }

        if (!elementIsVisible)
            Assert.fail("maxYear not found: " + maxYear);
    }

    public void selectMinPrice(String minPrice) {
        boolean elementIsVisible = false;
        bottomButton.get(2).click();
        bottomInput.get(2).sendKeys(minPrice);
        for (WebElement element : actions.waitForElements(By.tagName("a"))) {
            if (element.getText().equalsIgnoreCase(minPrice)) {
                element.click();
                elementIsVisible = true;
                break;
            }
        }

        if (!elementIsVisible)
            Assert.fail("minPrice not found: " + minPrice);
    }

    public void selectMaxPrice(String maxPrice) {
        boolean elementIsVisible = false;
        bottomButton.get(3).click();
        bottomInput.get(3).sendKeys(maxPrice);
        for (WebElement element : actions.waitForElements(By.tagName("a"))) {
            if (element.getText().equalsIgnoreCase(maxPrice)) {
                element.click();
                elementIsVisible = true;
                break;
            }
        }

        if (!elementIsVisible)
            Assert.fail("minPrice not found: " + maxPrice);
    }

    public void fillForm(String brand, String model, String minYear, String maxYear, String minPrice, String maxPrice) {
        selectBrand(brand);
        selectModel(model);
        selectMinYear(minYear);
        selectMaxYear(maxYear);
        selectMinPrice(minPrice);
        selectMaxPrice(maxPrice);
    }

    public void clickBtnSearch() {
        actions.click(btnSearch);
    }


}
