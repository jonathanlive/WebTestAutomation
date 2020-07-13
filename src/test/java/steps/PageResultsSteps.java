package steps;

import io.cucumber.java.en.Then;
import model.Car;
import org.junit.Assert;
import pages.BasePage;
import pages.PageResultsPage;
import support.core.ExecutionManager;
import java.util.List;

public class PageResultsSteps {

    private PageResultsPage pageResultsPage = BasePage.getInstance(PageResultsPage.class);

    @Then("the model1 (.*) with price1 (.*) and model2 (.*) with price2 (.*) will be displayed on the page results")
    public void theModel1WithPrice1AndModel2WithPrice2WillBeDisplayedOnThePageResults(String model1, String price1, String model2, String price2) {
        pageResultsPage.checkFirstAndSecondResult(model1, price1, model2, price2);
    }

    @Then("the page will be updated with a list of search matches")
    public void thePageResultWillBeDisplayedWithAListOfSearchMatches() {
        ExecutionManager.getInstance().saveCarsOnFile();
    }

    @Then("the page will be updated with the last search result")
    public void thePageWillBeUpdatedWithTheLastSearchResult() {
        List<Car> restoredCarList = ExecutionManager.getInstance().restoreCarsFromFile();
        List<Car> currentCarList = pageResultsPage.getAllPageResults();

        for (int i = 0; i < restoredCarList.size(); i ++){
            Assert.assertTrue("Todos os valores da pesquisa anterior devem ser iguais a pesquisa atual",restoredCarList.get(i).equals(currentCarList.get(i)));
        }
    }
}
