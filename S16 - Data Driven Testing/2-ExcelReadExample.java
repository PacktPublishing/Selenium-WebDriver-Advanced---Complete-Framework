package com.letskodeit.overview;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelReadExample {

    public static void main(String[] args) {
        XSSFWorkbook ExcelWBook;
        XSSFSheet ExcelWSheet;
        XSSFCell ExcelCell;

        String path = System.getProperty("user.dir") + "//src//main//resources//testdata//ExampleData.xlsx";
        String sheetName = "Scenario1";

        try {
            FileInputStream ExcelFile = new FileInputStream(path);
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(sheetName);
            ExcelCell = ExcelWSheet.getRow(0).getCell(0);

            String cellData = ExcelCell.getStringCellValue();
            System.out.println("Cell Data Value is: " + cellData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
