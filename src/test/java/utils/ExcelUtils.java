package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {

    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static CellStyle style;

    public static int getRowCount(String xlfile,String xlsheet) throws IOException
    {
        fi=new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fi);
        ws=wb.getSheet(xlsheet);
        int rowcount=ws.getLastRowNum();
        wb.close();
        fi.close();
        return rowcount;
    }


    public static int getCellCount(String xlfile,String xlsheet,int rownum) throws IOException
    {
        fi=new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fi);
        ws=wb.getSheet(xlsheet);
        row=ws.getRow(rownum);
        int cellcount=row.getLastCellNum();
        wb.close();
        fi.close();
        return cellcount;
    }


    public static String getCellData(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
    {
        fi=new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fi);
        ws=wb.getSheet(xlsheet);
        row=ws.getRow(rownum);
        cell=row.getCell(colnum);

        String data;
        try
        {
            //data=cell.toString();
            DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardless of the cell type.
        }
        catch (Exception e)
        {
            data="";
        }

        wb.close();
        fi.close();
        return data;
    }



    public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
    {
        fi=new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fi);
        ws=wb.getSheet(xlsheet);
        row=ws.getRow(rownum);

        cell=row.createCell(colnum);
        cell.setCellValue(data);
        fo=new FileOutputStream(xlfile);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();

    }

    public static void fillGreenColor(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
    {
        fi=new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fi);
        ws=wb.getSheet(xlsheet);
        row=ws.getRow(rownum);
        cell=row.getCell(colnum);

        style=wb.createCellStyle();

        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        fo=new FileOutputStream(xlfile);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }


    public static void fillRedColor(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
    {
        fi=new FileInputStream(xlfile);
        wb=new XSSFWorkbook(fi);
        ws=wb.getSheet(xlsheet);
        row=ws.getRow(rownum);
        cell=row.getCell(colnum);

        style=wb.createCellStyle();

        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        fo=new FileOutputStream(xlfile);
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }

    public static String[][] getSheetDataAsString(String xlfile, String xlsheet, boolean includeHeader) throws IOException {
        FileInputStream fis = null;
        XSSFWorkbook workbook = null;
        try {
            fis = new FileInputStream(xlfile);
            workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet(xlsheet);
            if (sheet == null) {
                return new String[0][0];
            }

            DataFormatter formatter = new DataFormatter();

            int lastRowNum = sheet.getLastRowNum();
            if (lastRowNum < 0) {
                return new String[0][0];
            }

            XSSFRow headerRow = sheet.getRow(0);
            int maxCols = headerRow != null ? headerRow.getLastCellNum() : 0;
            if (maxCols < 0) maxCols = 0;
            if (maxCols == 0) {
                return new String[0][0];
            }

            int startRow = includeHeader ? 0 : 1;
            int totalRowsAvailable = lastRowNum + 1;
            int rowsToRead = totalRowsAvailable - startRow;

            if (rowsToRead <= 0) {
                return new String[0][0];
            }

            String[][] data = new String[rowsToRead][maxCols];

            int destRow = 0;
            for (int r = startRow; r <= lastRowNum; r++) {
                XSSFRow row = sheet.getRow(r);
                for (int c = 0; c < maxCols; c++) {
                    String value = "";
                    if (row != null) {
                        XSSFCell cell = row.getCell(c);
                        if (cell != null) {
                            value = formatter.formatCellValue(cell);
                        }
                    }
                    data[destRow][c] = value != null ? value : "";
                }
                destRow++;
            }

            return data;
        } finally {
            if (workbook != null) workbook.close();
            if (fis != null) fis.close();
        }
    }



}
