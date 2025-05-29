package TestCases;

import Commnon.Constant;
import DataObjects.TableData;
import DataTypes.PageName;
import DataTypes.SortableDataTableHeader;
import PageObjects.HomePage;
import PageObjects.SortableTablePage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SortableDataTableTest {
    //ExtentTest extentTest;
    TableData actualFirstRow;
    TableData expectedFirstRow;
    SortableTablePage sortableTablePage;
    HomePage homePage;
    SoftAssert softAssert;
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Pre-condition");

        Constant.webDriver = new ChromeDriver();
        Constant.webDriver.manage().window().maximize();
        actualFirstRow = new TableData();
        expectedFirstRow = new TableData(
                "Smith",
                "John",
                "jsmith@gmail.com",
                "$50.00",
                "http://www.jsmith.com"
        );
        sortableTablePage = new SortableTablePage();
        softAssert = new SoftAssert();
        homePage = new HomePage();
        homePage.open();
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
//        Constant.webDriver.quit();
    }

    @Test(description = "Verify that the first table has correct data in the first row")
    public void TC1(){
        homePage.goToPage(PageName.SORTABLE_DATA_TABLE);
        actualFirstRow =  sortableTablePage.getFirstRowData();
        softAssert.assertEquals(actualFirstRow.getLastName(), expectedFirstRow.getLastName(), "Last name mismatch");
        softAssert.assertEquals(actualFirstRow.getFirstName(), expectedFirstRow.getFirstName(), "First name mismatch");
        softAssert.assertEquals(actualFirstRow.getEmail(), expectedFirstRow.getEmail(), "Email mismatch");
        softAssert.assertEquals(actualFirstRow.getDue(), expectedFirstRow.getDue(), "Due mismatch");
        softAssert.assertEquals(actualFirstRow.getWebsite(), expectedFirstRow.getWebsite(), "Website mismatch");
        softAssert.assertAll();
    }

    @Test(description = "Verify the data in the columns is sorted ASC/DESC when click on the header of each column")
    public void TC2(){
        homePage.goToPage(PageName.SORTABLE_DATA_TABLE);
        softAssert.assertTrue(sortableTablePage.isColumnSorted(SortableDataTableHeader.LAST_NAME));
        softAssert.assertTrue(sortableTablePage.isColumnSorted(SortableDataTableHeader.FIRST_NAME));
        softAssert.assertTrue(sortableTablePage.isColumnSorted(SortableDataTableHeader.EMAIL));
        softAssert.assertTrue(sortableTablePage.isColumnSorted(SortableDataTableHeader.DUE));
        softAssert.assertTrue(sortableTablePage.isColumnSorted(SortableDataTableHeader.WEBSITE));
        softAssert.assertAll();
    }
}
