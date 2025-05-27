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

public class SortableDataTableTest {
    //ExtentTest extentTest;
    TableData tableData;
    SortableTablePage sortableTablePage;
    HomePage homePage;
    @BeforeMethod
    public void beforeMethod(){
        System.out.println("Pre-condition");

        Constant.webDriver = new ChromeDriver();
        Constant.webDriver.manage().window().maximize();
        tableData = new TableData();
        sortableTablePage = new SortableTablePage();
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
        sortableTablePage.getAllDataOfTable1();
        TableData firstRow =  sortableTablePage.getAllDataOfTable1().get(0);
        Assert.assertEquals("Smith", firstRow.getLastName());
        Assert.assertEquals("John", firstRow.getFirstName());
        Assert.assertEquals("jsmith@gmail.com", firstRow.getEmail());
        Assert.assertEquals("$50.00", firstRow.getDue());
        Assert.assertEquals("http://www.jsmith.com", firstRow.getWebsite());
    }

    @Test(description = "Verify the data in the columns is sorted ASC/DESC when click on the header of each column")
    public void TC2(){
        homePage.goToPage(PageName.SORTABLE_DATA_TABLE);
        sortableTablePage.clickHeader(SortableDataTableHeader.LAST_NAME);
        sortableTablePage.clickHeader(SortableDataTableHeader.FIRST_NAME);
        sortableTablePage.clickHeader(SortableDataTableHeader.EMAIL);
        sortableTablePage.clickHeader(SortableDataTableHeader.DUE);
        sortableTablePage.clickHeader(SortableDataTableHeader.WEBSITE);
        sortableTablePage.clickHeader(SortableDataTableHeader.LAST_NAME);
    }
}
