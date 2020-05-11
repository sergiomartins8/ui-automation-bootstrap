package ${package}.utils.listeners;

import ${package}.base.DriverContext;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ${package}.utils.logging.Loggable;
import ${package}.utils.reports.ExtentManager;
import ${package}.utils.reports.ExtentTestReport;

import java.io.IOException;

/**
 * {@link ITestListener} implementation responsible for the test result reports.
 */
public class ExtentReportListener implements ITestListener, Loggable {

    @Override
    public void onStart(ITestContext iTestContext) {
        logger().debug(iTestContext.getName() + " starting");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        logger().debug(iTestContext.getName() + " finished");
        ExtentManager.getInstance().flush();
        ExtentTestReport.remoteTest();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        logger().debug("Test " + iTestResult.getName() + " starting");
        ExtentTestReport.createTest(iTestResult.getInstanceName(), iTestResult.getName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        logger().debug("Test " + iTestResult.getName() + " passed");
        ExtentTestReport.getTest().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        logger().error("Test " + iTestResult.getName() + " failed");

        ExtentTestReport.getTest().fail(MarkupHelper.createLabel(iTestResult.getThrowable().getMessage(), ExtentColor.RED));

        try {
            ExtentTestReport.getTest().fail("Screenshot - ",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(getScreenshotBase64()).build());
        } catch (IOException e) {
            logger().error(e.getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        logger().debug("Test " + iTestResult.getName() + " skipped");
        ExtentTestReport.getTest().skip("Test Skipped");
    }

    private String getScreenshotBase64() {
        return "data:image/png;base64," + ((TakesScreenshot) DriverContext
                .getRemoteWebDriver())
                .getScreenshotAs(OutputType.BASE64);
    }
}