package PageObjects;

import Commnon.Constant;
import DataObjects.TableData;
import DataTypes.SortableDataTableHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SortableTablePage extends GeneralPage {
    private final By rowsXpath = By.xpath("//table[@id='table1']/tbody/tr");
    private By headerXpath (String header){
        String xpath = String.format("//table[@id='table1']/thead/tr/th/span[text()='%s']", header);
        return By.xpath(xpath);
    }
    private By colsDataXpath (int index){
        String xpath = String.format("//table[@id='table1']/tbody/tr/td[%s]",index);
        return By.xpath(xpath);
    }

    protected WebElement getHeader(String header){
        return Constant.webDriver.findElement(headerXpath(header));
    }

    protected List<WebElement> getRowsOfTable1(){
        return Constant.webDriver.findElements(rowsXpath);
    }
    public List<TableData> getAllDataOfTable1(){
        List<TableData> tableDataList = new ArrayList<>();
        List<WebElement> rows = getRowsOfTable1();
        for(int i = 0; i < rows.size(); i++){
            List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
            String lastName = cols.get(0).getText();
            String firstName = cols.get(1).getText();
            String email = cols.get(2).getText();
            String due = cols.get(3).getText();
            String website = cols.get(4).getText();

            TableData tableData = new TableData(lastName, firstName, email, due, website);
            tableDataList.add(tableData);
        }
        return tableDataList;
    }

    public void clickHeader (SortableDataTableHeader header){
        getHeader(header.getHeader()).click();
    }

    public boolean isColumnSorted(SortableDataTableHeader header){
        getHeader(header.getHeader()).click();
        return true;
    }
}
