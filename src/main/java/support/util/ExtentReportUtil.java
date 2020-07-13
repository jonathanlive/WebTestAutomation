package support.util;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import model.BrowserType;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import support.actions.Actions;
import support.core.DriverManager;
import support.core.PropertieManager;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ExtentReportUtil {

    private static ExtentReportUtil _instance;
    private static ExtentReports _extent = new ExtentReports();
    private static boolean isReportCreated;
    private String browser;
    private Actions actions = new Actions();

    private ExtentReportUtil() {

    }

    public static ExtentReportUtil getInstance() {
        if (_instance == null)
            _instance = new ExtentReportUtil();

        return _instance;
    }


    public void createHtmlReporter(String pathname, String reportName, AnalysisStrategy analysisStrategy) {

        if (!isReportCreated) {
            System.out.println("--------- \n Realizando as configurações do relatório. \n --------- ");

            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(pathname);
            htmlReporter.config().setTheme(Theme.DARK);
            htmlReporter.config().setEncoding("utf-8");
            htmlReporter.config().setDocumentTitle(reportName);
            htmlReporter.config().setReportName(reportName);
            htmlReporter.setAnalysisStrategy(analysisStrategy);
            _extent.attachReporter(htmlReporter);
            try {
                _extent.setGherkinDialect("pt");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            browser = PropertieManager.getInstance().readProperties().getProperty("browserName");
            _extent.setSystemInfo("productName", PropertieManager.getInstance().readProperties().getProperty("productName"));
            _extent.setSystemInfo("productVersion", PropertieManager.getInstance().readProperties().getProperty("productVersion"));
            _extent.setSystemInfo("BrowserName", browser);

            isReportCreated = true;
            System.out.println("Relatório configurado com sucesso!");
        }
    }

    public ExtentTest createTest(String testName) {
        return _extent.createTest(testName);
    }

    public void captureImage(String filepath) {

        try {
            actions.waitForSeconds(1);
            File file = null;
            BrowserType browserType = BrowserType.valueOf(browser);
            switch (browserType) {
                case CHROME:
                    file = ((ChromeDriver) DriverManager.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
                    break;
                case FIREFOX:
                    file = ((FirefoxDriver) DriverManager.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
                    break;
                case IE:
                    file = ((InternetExplorerDriver) DriverManager.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
                    break;
                default:
                    Assert.fail("Browser informado não é suportado: "+browser);
            }
            FileUtils.copyFile(file, new File(filepath));
        } catch (IOException e) {
            System.out.println("Não foi possível criar o arquivo: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Erro ao capturar imagem: " + e.getMessage());
        }
    }

    public void attachFileToReport(ExtentTest test, String filepath) {
        try {
            test.addScreenCaptureFromPath(filepath, "Evidência");
        } catch (IOException e) {
            System.out.println("Imagem não localizada");
        }
    }

    public void flush() {
        System.out.println("Gravando as informações no relatório.");
        _extent.flush();
    }
}
