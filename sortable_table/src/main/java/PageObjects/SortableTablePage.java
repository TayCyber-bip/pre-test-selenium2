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
    private By headerXpath(String header) { return By.xpath(String.format("//table[@id='table1']/thead/tr/th/span[text()='%s']", header)); }
    private By colsDataXpath(int index) { return By.xpath(String.format("//table[@id='table1']/tbody/tr/td[%s]", index)); }


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
        return isSortedAsc(header) || isSortedDesc(header);
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
        List<String> sortedValues;
        List<Double> originalDoubles ;
        List<Double> sortedDoubles;

        if (header == SortableDataTableHeader.DUE) {
            originalDoubles = originalValues.stream()
                    .map(val -> Double.parseDouble(val.replace("$", "")))
                    .collect(Collectors.toList());

            sortedDoubles = new ArrayList<>(originalDoubles);
            Collections.sort(sortedDoubles);
            return originalDoubles.equals(sortedDoubles);
        } else {
            sortedValues = new ArrayList<>(originalValues);
            Collections.sort(sortedValues);
            return originalValues.equals(sortedValues);
        }
    }
    public boolean isSortedDesc(SortableDataTableHeader header) {
        List<String> originalValues = getColumnValues(header);
        List<String> sortedValues;
        List<Double> originalDoubles;
        List<Double> sortedDoubles;

        if (header == SortableDataTableHeader.DUE) {
            originalDoubles = originalValues.stream()
                    .map(val -> Double.parseDouble(val.replace("$", "")))
                    .collect(Collectors.toList());

            sortedDoubles = new ArrayList<>(originalDoubles);
            sortedDoubles.sort(Collections.reverseOrder());
            return originalDoubles.equals(sortedDoubles);
        } else {
            sortedValues = new ArrayList<>(originalValues);
            sortedValues.sort(Collections.reverseOrder());
            return originalValues.equals(sortedValues);
        }
    }
}
