package com.letskodeit.utilities;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

    private static XSSFWorkbook ExcelWBook;
    private static XSSFSheet ExcelWSheet;

    /*
     * Set the File path, open Excel file
     * @params - Excel Path and Sheet Name
     */
    public static void setExcelFile(String path, String sheetName) {
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(path);

            // Access the excel data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[][] getTestData(String tableName) {
        String[][] testData = null;

        try {
            // Handle numbers and strings
            DataFormatter formatter = new DataFormatter();
            // BoundaryCells are the first and the last column
            // We need to find first and last column, so that we know which rows to read for the data
            XSSFCell[] boundaryCells = findTableNameCells(tableName);
            // First cell to start with
            XSSFCell startCell = boundaryCells[0];
            // Last cell where data reading should stop
            XSSFCell endCell = boundaryCells[1];

            // Find the start row based on the start cell
            int startRow = startCell.getRowIndex() + 1;
            // Find the end row based on end cell
            int endRow = endCell.getRowIndex() - 1;
            // Find the start column based on the start cell
            int startCol = startCell.getColumnIndex() + 1;
            // Find the end column based on end cell
            int endCol = endCell.getColumnIndex() - 1;

            // Declare multi-dimensional array to capture the data from the table
            testData = new String[endRow - startRow + 1][endCol - startCol + 1];

            for (int i=startRow; i<endRow+1; i++) {
                for (int j=startCol; j<endCol+1; j++) {
                    // For every column in every row, fetch the value of the cell
                    Cell cell = ExcelWSheet.getRow(i).getCell(j);
                    // Capture the value of the cell in the multi-dimensional array
                    testData[i - startRow][j - startCol] = formatter.formatCellValue(cell);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Return the multi-dimensional array
        return testData;
    }

    public static XSSFCell[] findTableNameCells(String tableName) {
        DataFormatter formatter = new DataFormatter();
        // Declare begin position
        String pos = "begin";
        XSSFCell[] cells = new XSSFCell[2];

        for (Row row : ExcelWSheet) {
            for (Cell cell : row) {
                if (tableName.equals(formatter.formatCellValue(cell))) {
                    if (pos.equalsIgnoreCase("begin")) {
                        // Find the begin cell, this is used for boundary cells
                        cells[0] = (XSSFCell) cell;
                        pos = "end";
                    } else {
                        // Find the end cell, this is used for boundary cells
                        cells[1] = (XSSFCell) cell;
                    }
                }
            }
        }
        // Return the cells array
        return cells;
    }
}