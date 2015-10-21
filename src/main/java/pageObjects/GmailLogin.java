package main.java.pageObjects;

import main.java.utils.PageModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

/**
 * Created by Juan_Rodriguez on 10/9/2015.
 */
public class GmailLogin extends PageModel {


//    @FindBy(id = "Email")
//    private WebElement userNameTextBox;

    private WebElement userNameTextBox(){
        return driver.findElement(By.id("Email"));
    }

    private WebElement nextButton(){
        return driver.findElement(By.id("next"));
    }

    private WebElement passwordTextBox(){
        return driver.findElement(By.id("Passwd"));

    }

    private WebElement signInButton(){
        return driver.findElement(By.id("signIn"));
    }

    private WebElement rememberMeCheckBox(){
        return driver.findElement(By.id("PersistentCookie"));

    }

    public void logInWithUserNameAndPassword(String userName, String password,
                                             boolean beRemembered) {

        userNameTextBox().sendKeys(userName);
        nextButton().click();
        passwordTextBox().sendKeys(password);
        if (beRemembered)
            signInButton().click();
        else {
            rememberMeCheckBox().click();
            signInButton().click();
        }
    }

    public void navigateToGmail(){
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://gmail.com");
    }

}
