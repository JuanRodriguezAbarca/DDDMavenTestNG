package main.java.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Juan_Rodriguez on 10/9/2015.
 */
public class TheWebDriver {

    private static WebDriver driver;

    public static WebDriver getTheDriver() {
        if (driver == null) {
            driver = new FirefoxDriver();
            return driver;
        } else
            return driver;

    }

    public static void close(){
        getTheDriver().quit();
        driver = null;
    }
}
