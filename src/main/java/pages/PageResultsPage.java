package pages;

import model.Car;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import support.core.ExecutionManager;
import support.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PageResultsPage extends BasePage {

    @FindBy(css = "#ctdoTitle > div > h1 > span")
    private WebElement searchResults;

    @FindBy(css = "div > a > h2[class='esquerda titulo_anuncio']")
    private List<WebElement> model;

    @FindBy(css = "div > a > h3[class='direita preco_anuncio']")
    private List<WebElement> price;

    @FindBy(css = "div > div.clearfix.dados_anuncio > div.dados_veiculo > a > ul > li.primeiro")
    private List<WebElement> year;

    @FindBy(css = "div > div.clearfix.dados_anuncio > div.dados_veiculo > a > ul > li.usado")
    private List<WebElement> km;

    @FindBy(css = "div > div.clearfix.dados_anuncio > div.dados_veiculo > a > ul > li:nth-child(3)")
    private List<WebElement> color;

    @FindBy(css = "div > div.clearfix.dados_anuncio > div.dados_veiculo > a > ul > li.ultimo")
    private List<WebElement> exchange;

    public void checkFirstAndSecondResult(String model1, String price1, String model2, String price2) {
        int results = StringUtils.getDigits(actions.waitForElement(searchResults).getText());
        if(results == 0 )
            Assert.fail("no results");
        for (int i = 0; i < results; i++) {
            ExecutionManager.getInstance().addCar((new Car("Honda", model.get(i).getText(), price.get(i).getText(), year.get(i).getText(), km.get(i).getText(), color.get(i).getText(), exchange.get(i).getText())));
        }

        actions.scrollDownUntilIsVisible(model.get(0));
        Assert.assertEquals(model1, ExecutionManager.getInstance().getCars().get(0).getModel());
        Assert.assertTrue("First result price must be: "+ price1 , ExecutionManager.getInstance().getCars().get(0).getPrice().contains(price1));
        Assert.assertEquals(model2, ExecutionManager.getInstance().getCars().get(1).getModel());
        Assert.assertTrue("Second result price must be "+ price2, ExecutionManager.getInstance().getCars().get(1).getPrice().contains(price2));
    }

    public List<Car> getAllPageResults(){
        int results = StringUtils.getDigits(actions.waitForElement(searchResults).getText());
        if(results == 0 )
            Assert.fail("no results");
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < results; i++) {
            cars.add((new Car("Honda", model.get(i).getText(), price.get(i).getText(), year.get(i).getText(), km.get(i).getText(), color.get(i).getText(), exchange.get(i).getText())));
        }

        return cars;
    }
}
