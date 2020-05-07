package com.orgName.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.orgName.base.TestBase;

public class BankManagerLoginTest extends TestBase {
	
	
	@Test
	public void loginAsBankManager() {
		
		log.debug("Inside Login Test !!");
		click("bmlbutton_CSS");
		
		Assert.assertTrue(isElementPresent(By.cssSelector(or.getProperty("addCustBtn"))),"Login not successfull !!");
		
		log.debug("Login Test Completed !!");
		Reporter.log("Login Test Completed !!");
		//Reporter.log("<a target=\"_blank\" href=\"C:\\Users\\Ketan\\Pictures\\Screenshots\\Screenshot (2).png\">Screenshot</a>");
		System.out.println("BEFORE ALERT .");
		Assert.fail("LoginAsBankManager Test Failed....");
		System.out.println("AFTER ALERT .");
		
	}
	
	
}
