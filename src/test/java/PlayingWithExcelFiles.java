package test.java;

import main.java.utils.SpreadSheetEditor;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Juan_Rodriguez on 10/15/2015.
 */
public class PlayingWithExcelFiles {

    SpreadSheetEditor spEditor = new SpreadSheetEditor();


    @BeforeSuite(description = "Creating results File", enabled = true)
    public void preparingResultFile() throws IOException {
        if (!spEditor.clonningExcelFile("./src/main/resources/newFileParsing.xlsx", "./excelResults/clonnedFile.xlsx"))
            throw new SkipException("File was not clonned");
    }

    @Test(dataProvider = "readingTheClonned", priority = 2, enabled = true)
    public void verifyingTheClonnedFile(String rowID, String name, String password, String step1, String step2, String status) throws IOException {
        System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\n", rowID, name, password, step1, step2, status);

        if (spEditor.resultsWriter(rowID, (status.equalsIgnoreCase("pass"))))
            System.out.println("Result written");
        else
            System.out.println("Result not written");

        Assert.assertTrue(status.equalsIgnoreCase("pass"), "Test failed as 'pass' was not found");

    }


    @DataProvider(name = "readingTheClonned")
    private Object[][] readingTheClonned() throws IOException {
        return spEditor.getTableArray("./src/main/resources/newFileParsing.xlsx", true);
    }


    //TODO Try to implement the test in CI using Jenkins!!!
}
