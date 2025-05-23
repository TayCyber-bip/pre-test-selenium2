package TestCases;

import Commnon.Constant;
import DataObjects.TableData;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SortableDataTableTest {
    //ExtentTest extentTest;
    TableData tableData;
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Pre-condition");

        Constant.webDriver = new ChromeDriver();
        Constant.webDriver.manage().window().maximize();
        tableData = new TableData();

    }
    @AfterMethod
    public void afterMethod(ITestResult result) {
//        if (result.getStatus() == ITestResult.FAILURE) {
//            extentTest.fail("Test failed: " + result.getThrowable().getMessage());
//            String screenshotPath = ExtentManager.captureScreenshot(Constant.WEBDRIVER, result.getName());
//            extentTest.addScreenCaptureFromPath(screenshotPath);
//        } else if (result.getStatus() == ITestResult.SUCCESS) {
//            extentTest.pass("Test passed");
//        } else if (result.getStatus() == ITestResult.SKIP) {
//            extentTest.skip("Test skipped: " + result.getThrowable().getMessage());
//        }
//        ExtentManager.flush();
        Constant.webDriver.quit();
    }

    @Test(description = "Verify that the first table has correct data in the first row")
    public void TC1(){
        
    }
}
