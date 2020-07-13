package support.core;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import cucumber.api.PickleStepTestStep;
import cucumber.api.Result;
import cucumber.api.TestCase;
import cucumber.api.event.*;
import support.util.ExtentReportUtil;
import support.util.FileUtil;
import java.nio.file.FileSystems;

public class CustomPlugin implements EventListener {

    ExtentReportUtil extentReportUtil = ExtentReportUtil.getInstance();
    private ThreadLocal<ExtentTest> _test = new ThreadLocal<>();
    private ExtentTest _node;
    private ExtentTest _failedNode;
    private Markup _mkGreen = MarkupHelper.createLabel("Passed", ExtentColor.GREEN);
    private Markup _mkRed = MarkupHelper.createLabel("Failed", ExtentColor.RED);
    private Markup _mkOrange = MarkupHelper.createLabel("Skipped", ExtentColor.ORANGE);
    private String projectFolder = FileSystems.getDefault().getPath("").toAbsolutePath().toString();

    public CustomPlugin() {

    }

    public CustomPlugin(FileUtil file) {
    }

    private EventHandler<TestRunStarted> runStartedHandler = new EventHandler<TestRunStarted>() {
        @Override
        public void receive(TestRunStarted event) {
            try {
                startRun(event);
            } catch (Exception e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };

    private EventHandler<TestRunFinished> runFinishedHandler = new EventHandler<TestRunFinished>() {
        @Override
        public void receive(TestRunFinished event) {
            try {
                finishRun(event);
            } catch (Exception e) {
// TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };

    private EventHandler<TestCaseStarted> caseStartedHandler = new EventHandler<TestCaseStarted>() {
        @Override
        public void receive(TestCaseStarted event) {
            startCase(event);
        }
    };

    private EventHandler<TestCaseFinished> caseFinishedHandler = new EventHandler<TestCaseFinished>() {
        @Override
        public void receive(TestCaseFinished event) {
            finishCase(event);
        }
    };

    private EventHandler<TestStepStarted> stepStartedHandler = new EventHandler<TestStepStarted>() {
        @Override
        public void receive(TestStepStarted event) {
            startStep(event);
        }
    };

    private EventHandler<TestStepFinished> stepFinishedHandler = new EventHandler<TestStepFinished>() {
        @Override
        public void receive(TestStepFinished event) {
            finishStep(event);
        }
    };

    private void startRun(TestRunStarted event) {
        String reportPath = projectFolder + "/target/reports/webReport.html";
        extentReportUtil.createHtmlReporter(reportPath, "Web Report", AnalysisStrategy.BDD);
    }

    private void finishRun(TestRunFinished event) {
        extentReportUtil.flush();
    }

    private void startCase(TestCaseStarted event) {
        System.out.println(event.testCase.getName());
        ExtentTest test = extentReportUtil.createTest(event.testCase.getName());
        _test.set(test);
        _failedNode = null;
    }

    private void finishCase(TestCaseFinished event) {
        if (event.testCase instanceof TestCase) {
            String filepath = projectFolder + "/target/reports/screenshots/" +
                      "/" + FileUtil.createRandomFileName("evidence", ".png");
            String shortfilepath = filepath.replace(projectFolder + "/target/reports", ".");

            extentReportUtil.captureImage(filepath);

            if (event.result.getStatus().equals(Result.Type.PASSED)) {
                extentReportUtil.attachFileToReport(_node, shortfilepath);
            }

            if (event.result.getStatus().equals(Result.Type.FAILED)) {
                extentReportUtil.attachFileToReport(_failedNode, shortfilepath);
                _test.get().log(Status.ERROR,event.result.getError());
            }

            if (event.result.getStatus().equals(Result.Type.SKIPPED)) {
                extentReportUtil.attachFileToReport(_node, shortfilepath);
            }
        }

        extentReportUtil.flush();
    }

    private void startStep(TestStepStarted event) {
        if (event.testStep instanceof PickleStepTestStep) {
            PickleStepTestStep ev = (PickleStepTestStep) event.testStep;
            System.out.println(ev.getStepText());
            _node = _test.get().createNode(ev.getStepText());
        }
    }

    private void finishStep(TestStepFinished event) {
        if (event.testStep instanceof PickleStepTestStep) {

            if (event.result.getStatus().equals(Result.Type.PASSED)) {
                _node.pass(_mkGreen);
            }

            if (event.result.getStatus().equals(Result.Type.FAILED)) {
                _node.fail(_mkRed);
                if (_failedNode == null) {
                    _failedNode = _node;
                }
            }

            if (event.result.getStatus().equals(Result.Type.SKIPPED)) {
                _node.skip(_mkOrange);
            }
        }
    }

    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestRunStarted.class, runStartedHandler);
        publisher.registerHandlerFor(TestRunFinished.class, runFinishedHandler);
        publisher.registerHandlerFor(TestCaseStarted.class, caseStartedHandler);
        publisher.registerHandlerFor(TestCaseFinished.class, caseFinishedHandler);
        publisher.registerHandlerFor(TestStepStarted.class, stepStartedHandler);
        publisher.registerHandlerFor(TestStepFinished.class, stepFinishedHandler);
    }
}
