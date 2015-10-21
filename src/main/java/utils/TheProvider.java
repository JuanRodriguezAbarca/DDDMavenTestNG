package main.java.utils;

import jxl.read.biff.BiffException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Juan_Rodriguez on 10/6/2015.
 */
public class TheProvider {

    ExcelReader reader = new ExcelReader();
    SpreadSheetEditor spreadReader = new SpreadSheetEditor();

//    @DataProvider(name ="data-provider")
//    public Object[][] theProvider(String theFilePath) throws Exception {
//        return ExcelReader.getTableArray(theFilePath);
//    }

    @DataProvider(name = "data-provider")
    public Object[][] easyProvider() throws IOException, BiffException {

        Object[][] retObj = reader.getTheStringArrays("./src/main/resources/forParser.xls", "DataPool", "testData1");
        return retObj;

    }

    @DataProvider(name = "anySpreadSheetProvider")
    public Object[][] anyProvider() throws Exception {
        return spreadReader.getTableArray("./src/main/resources/newFileParsing.xlsx", true);
    }


    @DataProvider(name = "execute-strings")
    public Object[][] createGoodStrings() {
        return new Object[][]{new Object[]{"Ostias", "Putas"}, new Object[]{"Copon", "Bendito"}};
    }


    @Test(dataProvider = "anySpreadSheetProvider", enabled = true)
    public void printingFromSpreadSheet(String ex1, String ex2, String ex3, String ex4) {

        System.out.println(ex1);
        System.out.println(ex2);
        System.out.println(ex3);
        System.out.println(ex4);
        System.out.println();

    }


    @Test(dataProvider = "data-provider", enabled = false)
    public void printingFromExcelFile(String ex1, String ex2, String ex3, String ex4) {
        boolean matcher = true;

        System.out.println(ex1);
        System.out.println(ex2);
        System.out.println(ex3);
        System.out.println(ex4);
        System.out.println();


        if (ex1.contentEquals("finfantes") || ex2.contentEquals("finfantes") || ex3.contentEquals("finfantes") || ex4.contentEquals("finfantes")) {
            matcher = false;
        }

        Assert.assertTrue(matcher, "finfantes was found");
    }

    @Test(dataProvider = "execute-strings", dataProviderClass = TheProvider.class, enabled = false)
    public void printingFromTest(String content, String adj) {
        System.out.println(content + " " + adj);
    }
}
