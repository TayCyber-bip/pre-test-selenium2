package PageObjects;

import Commnon.Constant;

public class HomePage extends GeneralPage{

    public void open(){
        Constant.webDriver.navigate().to(Constant.THE_INTERNET_WEB);
    }
}
