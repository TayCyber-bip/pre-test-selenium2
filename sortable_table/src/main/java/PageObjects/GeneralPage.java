package PageObjects;

import Commnon.Constant;
import DataTypes.PageName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GeneralPage {
    private By getDynamicPageXpath(String pageName){
        String xpath = String.format("//a[text()='%s']", pageName);
        return By.xpath(xpath);
    }
    protected WebElement getPage(PageName page){
        return Constant.webDriver.findElement(getDynamicPageXpath(page.getName()));
    }

    public void goToPage(PageName page){
        this.getPage(page).click();
    }
}
