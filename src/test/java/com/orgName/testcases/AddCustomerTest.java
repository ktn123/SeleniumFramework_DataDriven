package com.orgName.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orgName.base.TestBase;
import com.orgName.utilities.TestUtil;

public class AddCustomerTest extends TestBase {
	
	@Test(dataProviderClass=TestUtil.class, dataProvider = "dp")
	public void addCustomer(String firstName, String lastName, String postcode, String alertText ) {
		
		//Method name should be same as sheetname.
		 
		click("addCustBtn_CSS");
		type("firstname_CSS", firstName);
		type("lastname_CSS", lastName);
		type("postcode_CSS", postcode);
		
		click("addBtn_CSS");
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		
		Assert.assertTrue(alert.getText().contains(alertText));
		alert.accept();
		
		log.debug("2nd test passed successfully..");
		
		
	}
	
	
		
//		for(int i=0;i<data.length;i++) {
//			for(int j=0;j<data.length;j++) {
//				System.out.println(data[i][j]);
//			}
//		}
		
		
		

}
