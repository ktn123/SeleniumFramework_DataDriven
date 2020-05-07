package com.orgName.listners;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.orgName.base.TestBase;
import com.orgName.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends TestBase implements ITestListener{
	
	public void onTestFailure(ITestResult arg0) {
		System.setProperty("org.uncommons.reportng.escape-output","false"); //Required for showing screenshot link declared below
		TestUtil.captureScreenshot();
	
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a target=\"_blank\" href="+TestUtil.screenshotName+"><img src=\"C:\\Users\\Ketan\\Pictures\\Screenshots\\Screenshot(2).png\" height=\"200px\" width=\"200px\"></img></a>");
		
		
		
		test.log(LogStatus.FAIL, arg0.getName().toUpperCase()+" Failed with exception : "+arg0.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		
		
		
		rep.endTest(test);
		rep.flush();
		
	}
	
	public void onTestStart(ITestResult arg0) {
		test = rep.startTest(arg0.getName().toUpperCase());
	}
	
	public void onTestSuccess(ITestResult arg0) {
		test.log(LogStatus.PASS, arg0.getName().toUpperCase()+"PASS");
		rep.endTest(test);
		rep.flush();
	}
	
}
