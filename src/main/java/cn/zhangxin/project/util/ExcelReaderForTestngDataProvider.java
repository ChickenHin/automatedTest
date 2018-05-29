package cn.zhangxin.project.util;

import java.io.FileInputStream;

import java.io.InputStream;



import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.DateUtil;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.apache.log4j.Logger;

import cn.zhangxin.project.util.Log4j;

public class ExcelReaderForTestngDataProvider {
    static private Logger excelReaderLogger;

    static private InputStream excelFileInputStream;



    /**

     * @param testDataFileName file name for test data, for example "testData1.xlsx". Please note:

     * 1. Better to use class name as excel file name;

     * 2. Method name as sheet name;

     * 3. Test data excel file should be put into "testdata" folder

     *

     * @param excelSheetName sheet to read test data

     * @return object[][] invoked by "@DataProvider" of TestNG.

     * @throws Exception

     */



    public static Object[][] getDataFrom(String testDataFileName, String excelSheetName) throws Exception{

        //Log4j to record logging

        excelReaderLogger = Log4j.logger(ExcelReaderForTestngDataProvider.class.getName());

        // Locate test data excel file

        String excelFileLocation = ProjectFile.returnPath("testdata", testDataFileName);

        // Check parameters provided by customer

        if (excelFileLocation.isEmpty() || excelSheetName.isEmpty())

        {

            excelReaderLogger.error("Excel file path or sheet name is empty.");

            throw new Exception("Must provide parameters: excelFileLocation and excelSheetName");

        }

        else if (!((excelFileLocation.substring(excelFileLocation.length()-5, excelFileLocation.length()).equals(".xlsx")) || (excelFileLocation.substring(excelFileLocation.length()-4, excelFileLocation.length()).equals(".xls"))))

        {

            excelReaderLogger.error("Excel file type of: " + testDataFileName + " is incorrect.");

            throw new Exception("The type of excel file: " +  testDataFileName  + " must be .xlsx or .xls;");

        }



        // Load the excel file

        try{

            excelFileInputStream = new FileInputStream(excelFileLocation);

            Workbook wb = WorkbookFactory.create(excelFileInputStream);

            excelReaderLogger.debug("Load the excel file " + testDataFileName);



            // read specific sheet designated by engineer

            Sheet sheet = wb.getSheet(excelSheetName);

            excelReaderLogger.debug("Load the sheet " + excelSheetName);



            // Get row and column number of this sheet

            // number of row (not include title row)

            int rowNumber = sheet.getLastRowNum();

            // number of column (calculate by title row)

            int columnNumber = sheet.getRow(0).getLastCellNum();

            // Create Object[][] to save all data, which will be invoked by @DataProvider of TestNG.

            Object[][] testData = new Object[rowNumber][columnNumber];



            // read from second row. The first row is used for column tile.

            for(int i = 1; i <= rowNumber; i++){

                Row row = sheet.getRow(i);



                //read every cell of each row.

                for (int j = 0; j < row.getLastCellNum(); j++){

                    excelReaderLogger.debug("Be reading the " + (i+1) + " row and " + (j+1) + " column.");

                    Cell cell = row.getCell(j, Row.RETURN_BLANK_AS_NULL);

                    if (cell == null)
                    {
                        testData[i-1][j] = null;
                        excelReaderLogger.debug("cell Row:"+String.valueOf(i)+",Column:"+String.valueOf(j)+" is empty, treat it as null and read next cell.");
                        continue;
                    }

                    //Judge cell type, then add to Object[][].

                    switch (cell.getCellType()) {

                        case Cell.CELL_TYPE_STRING:

                            testData[i-1][j] = cell.getStringCellValue();

                            excelReaderLogger.debug("read the cell value " + testData[i-1][j]);

                            break;

                        case Cell.CELL_TYPE_NUMERIC:

                            if (DateUtil.isCellDateFormatted(cell)) {

                                testData[i-1][j] = cell.getDateCellValue();

                                excelReaderLogger.debug("read the cell value " + testData[i-1][j]);

                            } else {

                                testData[i-1][j] = new Double(cell.getNumericCellValue());

                                excelReaderLogger.debug("read the cell value " + testData[i-1][j]);

                            }

                            break;

                        case Cell.CELL_TYPE_BOOLEAN:

                            testData[i-1][j] = new Boolean(cell.getBooleanCellValue());

                            excelReaderLogger.debug("read the cell value " + testData[i-1][j]);

                            break;

                        case Cell.CELL_TYPE_FORMULA:

                            testData[i-1][j] = cell.getCellFormula();

                            excelReaderLogger.debug("read the cell value " + testData[i-1][j]);

                            break;

                        default:

                            excelReaderLogger.error("Fail to read the " + (i+1) + " row and " + (j+1) + " column.");

                            throw new Exception("Incorrect Excel cell format");

                    }

                }

                excelReaderLogger.info("Read the " + (i+1) + " row.");



            }



            return testData;

        }

        catch (Exception e){

            e.printStackTrace();

            throw new Exception("Fail to read excel sheet: " + excelSheetName + " from file: " + testDataFileName);

        }

        finally{

            //close excel file

            excelFileInputStream.close();

            excelReaderLogger.info("Close the excel file: " + testDataFileName);

        }

    }

}
