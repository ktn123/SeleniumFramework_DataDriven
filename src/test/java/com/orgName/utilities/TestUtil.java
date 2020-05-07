package com.orgName.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
//import org.apache.commons.*;
import org.testng.annotations.DataProvider;

import com.orgName.base.TestBase;

public class TestUtil extends TestBase {

	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() {
		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			Date d = new Date();
			screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

			File destFile = new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName);
			System.out.println("DEST FILE CREATED.......");

			FileHandler.copy(srcFile, destFile);
		} catch (IOException e) {
			System.out.println("Not able to copy : Error in TestUtils class.");
			e.printStackTrace();
		}
	}
	
	@DataProvider(name= "dp")
	public Object[][] getData(Method m){    //method name on which the data provider is used. Name should be same as Sheetname 
		String sheetName = m.getName();
		System.out.println(sheetName);
		int rows = excel.getRowCount(sheetName);  //1
		int cols = excel.getColumnCount(sheetName); //3
		System.out.println("Rows : "+rows);
		System.out.println("Cols : "+cols);
		
		Object[][] data = new Object[rows][cols];
		
		for(int i=1;i<=rows;i++) {
			for(int j=0;j<cols;j++) {
				data[i-1][j] = excel.getCellData(sheetName, i, j);
				//System.out.println("data["+(i-1)+"]["+j+"]"+" == "+excel.getCellData(sheetName, i, j));
			}
			
		}
		return data;
	}
	
}
