package com.orgName.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileout = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	
	//constructor
	public ExcelReader(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Check if sheet exists or not.
	public boolean sheetExists(String sheetName) {
		int sheetIndex = workbook.getSheetIndex(sheetName);
		if(sheetIndex == -1) {
			sheetIndex = workbook.getSheetIndex(sheetName.toUpperCase());
			if(sheetIndex == -1) {
				return true;
			}else {
				return false;
			}
		}else {
			return true;
		}
		
	}
	
	//Method that return rowcount in a sheet.
	public int getRowCount(String sheetName) {
		int sheetIndex = workbook.getSheetIndex(sheetName); 
		if(sheetIndex == -1) {
			return 0;
		}else {
			sheet = workbook.getSheetAt(sheetIndex);
			int count = sheet.getLastRowNum();
			return count;
		}
	}
	
	// returns number of columns in a sheet	
		public int getColumnCount(String sheetName){
			// check if sheet exists
			if(!sheetExists(sheetName))
			 return -1;
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			if(row==null)
				return -1;
			return row.getLastCellNum();
		}
		
		//read cell Data...
		public String getCellData(String sheetName, int rownum, int colnum) {
			String cellValue ="";
			if(sheetExists(sheetName)) {
				sheet = workbook.getSheet(sheetName);
				row = sheet.getRow(rownum);
				cell = row.getCell(colnum);
				//System.out.println(cell);
				if(cell != null)
					cellValue = cell.toString();
				else
					cellValue = "------";
			}else {
				cellValue = "Sheet Not Found"; 
			}
			//System.out.println("this is cell value for :"+rownum+" "+colnum+" "+cellValue);
			return cellValue;
		}
		
	
//		public static void main(String[] args) {
//			ExcelReader read = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\Excels\\TestData.xlsx");
//			System.out.println("Row count : "+read.getRowCount("AddCustomerTest"));
//			System.out.println("Column count : "+read.getColumnCount("AddCustomerTest"));
//			for(int i=1;i<=read.getRowCount("AddCustomerTest");i++) {
//				for(int j=0;j<read.getColumnCount("AddCustomerTest");j++) {
//					System.out.println("Row: "+i+" col: "+j+" "+read.getCellData("AddCustomerTest", i, j));
//				}
//				System.out.println("Row ends -----------------------");
//			}
//		}
//	
	
	
}
