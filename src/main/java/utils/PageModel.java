package main.java.utils;

import org.openqa.selenium.WebDriver;

/**
 * Created by Juan_Rodriguez on 10/9/2015.
 */
public abstract class PageModel {


    public WebDriver driver = TheWebDriver.getTheDriver();

    public void navigate(String url){
        driver.get(url);
    }

    public void close(){
        TheWebDriver.close();
    }
}
