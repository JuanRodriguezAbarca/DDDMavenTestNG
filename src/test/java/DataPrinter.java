package test.java;

import main.java.utils.ExcelReader;
import main.java.utils.TheProvider;
import org.testng.annotations.Test;

/**
 * Created by Juan_Rodriguez on 10/6/2015.
 */
public class DataPrinter {

    @Test(dataProvider = "execute-strings", dataProviderClass = TheProvider.class)
    public void printingFromProvider(String content, String adj){
        System.out.println(content+" "+adj);

    }
}
