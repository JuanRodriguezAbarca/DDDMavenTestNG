package main.java.utils;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Juan_Rodriguez on 10/6/2015.
 */
public class ExcelReader {



    public String[][] getTheStringArrays(String xlFilePath, String sheetName, String tableName) throws IOException, BiffException {
        String[][] tabArray;

        Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
        Sheet sheet = workbook.getSheet(sheetName);
        int startRow, startCol, endRow, endCol, ci, cj;
        Cell tableStart = sheet.findCell(tableName);
        startRow = tableStart.getRow();
        startCol = tableStart.getColumn();

        Cell tableEnd = sheet.findCell(tableName, startCol + 1, startRow + 1, 100, 6400, false);

        endRow = tableEnd.getRow();
        endCol = tableEnd.getColumn();
        System.out.println("startRow= " + startRow + ", endRow= " + endRow + ", startCol= " +
                startCol + ", endCol= " + endCol);
        tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
        ci = 0;

        for (int i = startRow + 1; i < endRow; i++, ci++) {
            cj = 0;
            for (int j = startCol + 1; j < endCol; j++, cj++) {
                tabArray[ci][cj] = sheet.getCell(j, i).getContents();
            }
        }
        System.out.println("Number of arrays: " + tabArray.length);
        System.out.println("The Array of arrays:");
        for (int i = 0; i < tabArray.length; i++) {
            System.out.println(Arrays.toString(tabArray[i]));
        }
        return tabArray;

    }


//    @DataProvider(name = "data-provider")
//    public Object[][] easyProvider() throws IOException, BiffException {
//        return getTheStringArrays("D:\\forPaser.xlsx", "DataPool", "testData1");
//    }


    @DataProvider(name = "data-provider")
    public Object[][] easyProvider(Method m) {
        System.out.println(m.getName());
        return new Object[][]
                {new Object[]{"Mindas", "chungas"},
                        {"perros", "feos"}};
    }

}
