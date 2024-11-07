package Base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

import static java.lang.System.getProperty;

public class BaseClass implements ITestListener {
    public static WebDriver driver;
    public static WebDriverWait wait;
    protected Properties properties;
    protected Logger logger;
    public static ExtentReports extent;
    public ExtentTest extentTest;


    // Constructor to initialize logger and configure log4j properties
    @BeforeTest
    public void BaseTest() {
        // Initialize the logger for the specific test class
        logger = Logger.getLogger(String.valueOf(getClass()));
        // Configure log4j using the properties file
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
    }

    @BeforeTest
    public void setUp() {
        loadConfigProperties();
        initializeDriver();
    }



    protected void loadConfigProperties() {
        properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration properties.");
        }
    }



    protected void initializeDriver() {
        String baseUrl = properties.getProperty("baseUrl");
        //  String baseUrl = "https://www.google.com/";
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
     //   driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().maximize();
        driver.get(baseUrl);

    }
    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    /*
    @AfterMethod
    public void captureTestResults(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail("Test Case Failed is " + result.getName()); // Log failure message
            test.fail("Test Case Failed with error: " + result.getThrowable()); // Log the exception
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Case Passed is " + result.getName()); // Log success message
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test Case Skipped is " + result.getName()); // Log skip message
        }
    }
        @AfterTest
        public void tearDownExtentReport () {
            extent.flush();
        }
*/
    @BeforeSuite
    public void setupExtentReport() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Environment", "Test Automation");
        extent.setSystemInfo("Tester", "John Doe");
    }

    @AfterSuite
    public void tearDownExtentReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Start the ExtentTest for this test case
        extentTest = extent.createTest(result.getMethod().getMethodName());
        extentTest.info("Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log success message
        extentTest.pass("Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log failure message and capture exception
        extentTest.fail("Test Failed: " + result.getMethod().getMethodName());
        extentTest.fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Log skip message
        extentTest.skip("Test Skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        // Finalize Extent Report
        if (extent != null) {
            extent.flush();
        }
    }

}




