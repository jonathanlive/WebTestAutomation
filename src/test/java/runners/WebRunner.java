package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import support.core.ExecutionManager;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = "./src/test/resources/features",
        glue = "steps",
        plugin = "support.core.CustomPlugin",
        monochrome = false,
        dryRun = false,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        tags = "@test"

)
public class WebRunner {

    @BeforeClass
    public static void setUp(){
        ExecutionManager.getInstance().startExecution();
    }

    @AfterClass
    public static void tearDown(){
        ExecutionManager.getInstance().stopExecution();
    }

}
