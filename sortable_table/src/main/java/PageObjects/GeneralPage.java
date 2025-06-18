package PageObjects;

import Commnon.Constant;
import DataTypes.PageName;
import org.openqa.selenium.By;

public class GeneralPage {
    private By getDynamicPageXpath(PageName page){
        String xpath = String.format("//a[text()='%s']", page.getName());
        return By.xpath(xpath);
    }

    public void goToPage(PageName page){
        Constant.webDriver.findElement(getDynamicPageXpath(page)).click();
    }
}
