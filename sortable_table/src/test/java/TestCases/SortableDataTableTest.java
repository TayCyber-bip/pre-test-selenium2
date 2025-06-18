package TestCases;

import Commnon.Constant;
import Commnon.PropertyReader;
import Commnon.util.ExtentManager;
import DataObjects.TableData;
import DataTypes.PageName;
import DataTypes.SortableDataTableHeader;
import PageObjects.HomePage;
import PageObjects.SortableTablePage;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Properties;

public class SortableDataTableTest {
    //ExtentTest extentTest;
    TableData actualFirstRow;
    TableData expectedFirstRow;
    SortableTablePage sortableTablePage;
    HomePage homePage;
    SoftAssert softAssert;
    ExtentTest extentTest;
    Properties props;
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Pre-condition");

        Constant.webDriver = new ChromeDriver();
        Constant.webDriver.manage().window().maximize();
        actualFirstRow = new TableData();
        props= PropertyReader.loadProperties("Data/expectedRow.properties");
        expectedFirstRow = new TableData(
                props.getProperty("lastName"),
                props.getProperty("firstName"),
                props.getProperty("email"),
                props.getProperty("amount"),
                props.getProperty("website")
        );
        sortableTablePage = new SortableTablePage();
        softAssert = new SoftAssert();
        homePage = new HomePage();
        homePage.open();
    }
    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail("Test failed: " + result.getThrowable().getMessage());
            String screenshotPath = ExtentManager.captureScreenshot(Constant.webDriver, result.getName());
            extentTest.addScreenCaptureFromPath(screenshotPath);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.skip("Test skipped: " + result.getThrowable().getMessage());
        }
        ExtentManager.flush();
        Constant.webDriver.quit();
    }

    @Test(description = "Verify that the first table has correct data in the first row")
    public void TC1(){
        extentTest = ExtentManager.createTest("Pretest: TC1","Verify that the first table has correct data in the first row");
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
        extentTest = ExtentManager.createTest("Pretest: TC2","Verify the data in the columns is sorted ASC/DESC when click on the header of each column");
        homePage.goToPage(PageName.SORTABLE_DATA_TABLE);
        softAssert.assertTrue(sortableTablePage.isColumnSorted(SortableDataTableHeader.LAST_NAME));
        softAssert.assertTrue(sortableTablePage.isColumnSorted(SortableDataTableHeader.FIRST_NAME));
        softAssert.assertTrue(sortableTablePage.isColumnSorted(SortableDataTableHeader.EMAIL));
        softAssert.assertTrue(sortableTablePage.isColumnSorted(SortableDataTableHeader.DUE));
        softAssert.assertTrue(sortableTablePage.isColumnSorted(SortableDataTableHeader.WEBSITE));
        softAssert.assertAll();
    }
}
