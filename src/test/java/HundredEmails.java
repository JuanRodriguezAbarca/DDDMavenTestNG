package test.java;

import main.java.pageObjects.GmailLogin;
import main.java.pageObjects.GmailMailBox;
import main.java.utils.SpreadSheetEditor;
import org.testng.annotations.*;

import java.io.IOException;

/**
 * Created by Juan_Rodriguez on 10/9/2015.
 */
public class HundredEmails {


    SpreadSheetEditor spreadSheetReader = new SpreadSheetEditor();
    GmailLogin login = new GmailLogin();
    GmailMailBox mailing = new GmailMailBox();

    @BeforeTest
    public void loadingAndOpening() {
        login.navigateToGmail();
    }

    @AfterClass
    public void closingTheBrowser() {
        login.close();
    }

    @BeforeClass
    public void logingTheUser() {
        login.logInWithUserNameAndPassword("cabronenmascaradovengador", "PutoCabron:)", false);

    }


    @Test(description = "Reading file and sending 100 mails", dataProvider = "anySpreadSheetProvider")
    public void sendingHundredMails(String recipient, String subject, String body) throws InterruptedException {
        mailing.sendingMail(recipient, subject, body);
    }


    @DataProvider(name = "anySpreadSheetProvider")
    private Object[][] anyProvider() throws Exception {
        return spreadSheetReader.getTableArray("./src/main/resources/mails.xlsx",true);
    }

}
