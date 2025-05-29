package PageObjects;

import Commnon.Constant;
import DataObjects.TableData;
import DataTypes.SortableDataTableHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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


    public List<TableData> getAllDataOfTable1(){
        List<TableData> tableDataList = new ArrayList<>();
        List<WebElement> rows = Constant.webDriver.findElements(rowsXpath);
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
    public TableData getFirstRowData(){
        return getAllDataOfTable1().get(0);
    }

    public void clickHeader (SortableDataTableHeader header){
        Constant.webDriver.findElement(headerXpath(header.getHeader())).click();
    }

    public boolean isColumnSorted(SortableDataTableHeader header){
        clickHeader(header);
        boolean sortedAsc = isSortedAsc(header);
        clickHeader(header);
        boolean sortedDesc = isSortedDesc(header);
        return sortedAsc && sortedDesc;
    }

    public List<String> getColumnValues(SortableDataTableHeader header){
        List<String> colValues = new ArrayList<>();
        List<WebElement> rows = Constant.webDriver.findElements(rowsXpath);
        for(int i = 0; i < rows.size(); i++){
            String values = Constant.webDriver.findElement(colsDataXpath(header.getColIndex())).getText();
            colValues.add(values);
        }
        return colValues;
    }
    public boolean isSortedAsc(SortableDataTableHeader header) {
        List<String> originalValues = getColumnValues(header);

        if (header == SortableDataTableHeader.DUE) {
            List<Double> original = originalValues.stream()
                    .map(val -> Double.parseDouble(val.replace("$", "")))
                    .collect(Collectors.toList());

            List<Double> sorted = new ArrayList<>(original);
            Collections.sort(sorted);
            return original.equals(sorted);
        } else {
            List<String> sorted = new ArrayList<>(originalValues);
            Collections.sort(sorted);
            return originalValues.equals(sorted);
        }
    }
    public boolean isSortedDesc(SortableDataTableHeader header) {
        List<String> originalValues = getColumnValues(header);

        if (header == SortableDataTableHeader.DUE) {
            List<Double> original = originalValues.stream()
                    .map(val -> Double.parseDouble(val.replace("$", "")))
                    .collect(Collectors.toList());

            List<Double> sorted = new ArrayList<>(original);
            sorted.sort(Collections.reverseOrder());
            return original.equals(sorted);
        } else {
            List<String> sorted = new ArrayList<>(originalValues);
            sorted.sort(Collections.reverseOrder());
            return originalValues.equals(sorted);
        }
    }
}
