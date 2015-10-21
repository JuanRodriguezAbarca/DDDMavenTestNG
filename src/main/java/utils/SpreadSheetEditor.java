package main.java.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Juan_Rodriguez on 10/7/2015.
 */
public class SpreadSheetEditor {

    public Object[][] getTableArray(String filePath, boolean toTest) throws IOException {
        int titleReader = 0;
        Object[][] retArray;
        List<String> tempCellString = new ArrayList<>();
        List<Object[]> tempColString = new ArrayList<>();


        FileInputStream excelFile = new FileInputStream(new File(filePath));

        Workbook workBook = new XSSFWorkbook(excelFile);
        Sheet excelSheet = workBook.getSheetAt(0);

        Iterator<Row> rowIterator = excelSheet.iterator();

        int ci = 0;
        while (rowIterator.hasNext()) {
            tempCellString.clear();
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            ci++;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getCellType() == 0) {
                    int temp = (int) cell.getNumericCellValue();
                    tempCellString.add(String.valueOf(temp));
                } else {
                    tempCellString.add(cell.getStringCellValue());
                }

            }

            tempColString.add(tempCellString.toArray());
        }

        if (toTest) {
            titleReader = 1;
        }
        retArray = new Object[ci - titleReader][];

        for (int i = 0; i < retArray.length; ++i) {
            retArray[i] = tempColString.get(i + titleReader);
//            System.out.println(Arrays.toString(retArray[i]));
        }
//        System.out.println();

        excelFile.close();
        return retArray;

    }

    public boolean clonningExcelFile(String origin, String result) throws IOException {
        Object[][] theTestData = getTableArray(origin, false);
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("TestResults");
            for (int i = 0; i <= theTestData.length - 1; i++) {
                XSSFRow row = sheet.createRow(i);

                for (int j = 0; j <= theTestData[i].length - 1; j++) {
                    row.createCell(j).setCellValue(theTestData[i][j].toString());
                }
                if (i == 0) {
                    row.createCell(theTestData[i].length).setCellValue("Result");
                }
            }
            FileOutputStream filOut = new FileOutputStream(result);
            workbook.write(filOut);
            filOut.close();
            System.out.println("File " + origin + " clonned in " + result);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean resultsWriter(String row, boolean status) throws IOException {
        int resultIndex = 0;

        try {
            FileInputStream results = new FileInputStream(new File("./excelResults/clonnedFile.xlsx"));
            Workbook workbook = new XSSFWorkbook(results);
            Sheet sheet = workbook.getSheetAt(0);

            Row headerRow = sheet.getRow(0);

            resultIndex = headerRow.getLastCellNum();

            Row rowSheet = sheet.getRow(Integer.parseInt(row));

            rowSheet.createCell(resultIndex-1).setCellType(1);

            rowSheet.getCell(resultIndex-1).setCellValue(status ? "Pass" : "Fail");


            results.close();

            FileOutputStream printResults = new FileOutputStream(new File("./excelResults/clonnedFile.xlsx"));
            workbook.write(printResults);

            printResults.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}