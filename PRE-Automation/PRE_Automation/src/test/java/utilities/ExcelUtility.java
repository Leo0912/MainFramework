package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import junit.framework.Assert;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;


public class ExcelUtility {
	/**
	 * Read Excel value's and Return a Constructor
	 * @param excelFilePath
	 * @param sheetName
	 * @throws IOException
	 */
	public LinkedHashMap<Object, LinkedHashMap<Object, Object>> readExcel(String excelFilePath,String sheetName) throws IOException{
		LinkedHashMap<Object, LinkedHashMap<Object, Object>> excelCollection=new LinkedHashMap<Object, LinkedHashMap<Object, Object>>();
		try {
			FileInputStream ExcelFile = new FileInputStream(excelFilePath);
			 Workbook wb = new XSSFWorkbook (ExcelFile);
			 Sheet mySheet = wb.getSheet(sheetName);
			 
			 for(int row=1;row<=mySheet.getLastRowNum();row++){
				 LinkedHashMap<Object, Object> secondaryHashmap=new LinkedHashMap<Object, Object>();
				 Object primaryKey= fetchCellValue(mySheet.getRow(row).getCell(0));
			     for(int col=1;col<mySheet.getRow(0).getLastCellNum();col++){
			    	Object secondaryKey= fetchCellValue(mySheet.getRow(0).getCell(col));
			    	Object value= fetchCellValue(mySheet.getRow(row).getCell(col));
			    	secondaryHashmap.put(secondaryKey, value);
			     }             
			     excelCollection.put(primaryKey, secondaryHashmap) ;
			 }
		} catch (IOException e) {
			Reporter.log("readExcel: Method had Exceptions >>"+e.getMessage());
			Assert.fail("readExcel: Method had Exceptions >>"+e.getMessage());
		}
		return excelCollection;
	}
	/**
	 * Used to fetch value's from Cell based on Cell Type
	 * @return
	 */
	public Object fetchCellValue(Cell cell){
		Object cellValue = "No_Value";
		 try {
			CellType type = cell.getCellTypeEnum();
			 if (type == CellType.STRING) {
				 cellValue=cell.getStringCellValue();
			 } else if (type == CellType.NUMERIC) {
				 cellValue=cell.getNumericCellValue();
			 } else if (type == CellType.BOOLEAN) {
				 cellValue=cell.getBooleanCellValue();
			 } else if (type == CellType.BLANK) {
				 cellValue="No_Value";
			 }
		} catch (Exception e) {
			Reporter.log("fetchCellValue: Method had Exceptions >>"+e.getMessage());
			Assert.fail("fetchCellValue: Method had Exceptions >>"+e.getMessage());
		}
		return cellValue;
		
	}
	
	public void writeSubs3Excel(String Filename, Map<String, Object[]> data) throws Exception{
		FileInputStream fsIP= new FileInputStream(new File(Filename));  
		//Access the workbook                  
		XSSFWorkbook wb = new XSSFWorkbook(fsIP);
		//Access the worksheet, so that we can update / modify it. 
		XSSFSheet sheet = wb.getSheetAt(0); 
		Set<String> keyset = data.keySet();
		int rownum = sheet.getPhysicalNumberOfRows();
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if  (obj instanceof Boolean) {
                    cell.setCellValue((Boolean) obj);
                } else if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Double) {
                    cell.setCellValue((Double) obj);
                }
            }
        }
        try {
            FileOutputStream out
                    = new FileOutputStream(new File(Filename));
            wb.write(out);
            out.close();
            System.out.println("Excel written successfully..");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	

}
