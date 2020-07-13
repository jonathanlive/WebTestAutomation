package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.BasePage;
import pages.HomePage;
import support.core.DriverManager;


public class HomePageSteps {

    private String baseURL = "https://www.icarros.com.br/principal/index.jsp";
    private HomePage homePage = BasePage.getInstance(HomePage.class);

    @Given("user is on the homepage")
    public void userIsOnTheHomepage() {
        DriverManager.getInstance().getDriver().get(baseURL);
    }

    @When("the user fulfill the form with brand (.*) model (.*) minYear (.*) maxYear (.*) minPrice (.*) maxPrice (.*$)")
    public void theUserFulfillTheFormWithDesiredInformation(String brand, String model, String minYear, String maxYear, String minPrice, String maxPrice) {
        homePage.fillForm(brand, model, minYear, maxYear, minPrice, maxPrice);
    }

    @And("click on search button")
    public void clickOnSearchButton() {
        homePage.clickBtnSearch();
    }

}
