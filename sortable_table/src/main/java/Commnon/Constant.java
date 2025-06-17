package Commnon;

import org.openqa.selenium.WebDriver;

import java.time.format.DateTimeFormatter;

public class Constant {
    public static WebDriver webDriver;
    public static final String THE_INTERNET_WEB = "https://the-internet.herokuapp.com/";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
}
