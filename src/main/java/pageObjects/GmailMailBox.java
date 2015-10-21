package main.java.pageObjects;

import main.java.utils.PageModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Juan_Rodriguez on 10/9/2015.
 */
public class GmailMailBox extends PageModel {

    private WebElement composeButton() {
        return driver.findElement(By.xpath("//div[@gh='cm']"));

    }

    private WebElement recipientsTextBox() {
        return driver.findElement(By.xpath("//div[@class='AD']/div/div/div[3]//table//table//textarea"));

    }


    private WebElement emailSubjectTextBox() {
        return driver.findElement(By.xpath("//div[@class='AD']/div/div/div[3]//form/div[3]/input"));
    }

    private WebElement bodyMailTextBox() {
        return driver.findElement(By.xpath("//div[@aria-label='Message Body']"));
    }

    private WebElement sendButton() {
        return driver.findElement(By.xpath("//div[@class='AD']/div/div/div[3]//table[@class='iN']//tr[2]/td[1]/div/div/div[4]//tbody//td[1]/div[1]/div[2]"));
    }


    public void sendingMail(String recipient, String subject, String body) throws InterruptedException {
        composeButton().click();
        recipientsTextBox().sendKeys(recipient);
        emailSubjectTextBox().sendKeys(subject);
        bodyMailTextBox().click();
        bodyMailTextBox().sendKeys(body);
        sendButton().click();
        Thread.sleep(1000);
    }


}
