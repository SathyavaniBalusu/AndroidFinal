package ExcelValidation;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelOperation {
    private static XSSFSheet ExcelWSheet;

    private static XSSFWorkbook ExcelWBook;

    private static XSSFCell Cell;



    //This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method

    public static void setExcelFile(String Path,String SheetName) throws Exception {

        try {

            // Open the Excel file

            FileInputStream ExcelFile = new FileInputStream(Path);

            // Access the required test data sheet

            ExcelWBook = new XSSFWorkbook(ExcelFile);

            ExcelWSheet = ExcelWBook.getSheet(SheetName);

        } catch (Exception e){

            throw (e);

        }

    }

    public static int cols(){
        XSSFRow row = ExcelWSheet.getRow(0);
        int Cols = row.getLastCellNum();
        return Cols;
    }
    public static int rows(){
        XSSFRow row = ExcelWSheet.getRow(0);
        int rows = ExcelWSheet.getLastRowNum()+1;
        return rows;
    }


    public static Object[][] getTableArray(String FilePath, String SheetName)    throws Exception

    {

        String[][] tabArray = null;

        try{

            FileInputStream ExcelFile = new FileInputStream(FilePath);

            // Access the required test data sheet

            ExcelWBook = new XSSFWorkbook(ExcelFile);

            ExcelWSheet = ExcelWBook.getSheet(SheetName);

            int startCol = 0;
            int startRow =1;

            XSSFRow row = ExcelWSheet.getRow(0);
            int totalCols = row.getLastCellNum();
            System.out.println("Total Number of Columns in the excel is : "+totalCols);
            int totalRows = ExcelWSheet.getPhysicalNumberOfRows();
            System.out.println("Total Number of Rows in the excel is : "+totalRows);
            tabArray=new String[totalRows][totalCols];

            for (int i=startRow;i<totalRows;i++) {
                for(int j=startCol;j<totalCols;j++)

                {
                    tabArray[i-1][j]= getCellData(i,j);
                    //System.out.println (tabArray[i][j]);

                }
            }
        }

        catch (FileNotFoundException e)

        {

            System.out.println("Could not read the Excel sheet");

            e.printStackTrace();

        }

        catch (IOException e)

        {

            System.out.println("Could not read the Excel sheet");

            e.printStackTrace();

        }
        return(tabArray);

    }

    //This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num

    public static String getCellData(int RowNum, int ColNum) throws Exception {

        try {

            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

            String CellData = Cell.getStringCellValue();
            return CellData;

        } catch (Exception e) {

            return "";

        }
    }
}
