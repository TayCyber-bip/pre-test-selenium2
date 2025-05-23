package PageObjects;

import Commnon.Constant;
import DataObjects.TableData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SortableTablePage extends GeneralPage {
    private By dataOfTable1Xpath (int row){
        String xpath = String.format("//table[@id='table1']/tbody/tr['%s']/td");
        return By.xpath(xpath);
    }

//    protected WebElement getTable1Data (){
//        return Constant.webDriver.findElement(dataOfTable1Xpath())
//    }

    public void checkData(TableData data){

    }
}
