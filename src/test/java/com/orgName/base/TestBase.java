package com.orgName.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.orgName.utilities.ExcelReader;
import com.orgName.utilities.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

//import jdk.internal.org.jline.utils.Log;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\Excels\\TestData.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	@BeforeSuite
	public void setUp() {
		if(driver==null) {
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file Loaded!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\Properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				or.load(fis);
				log.debug("OR file Loaded!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(config.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") +"\\src\\test\\resources\\executables\\chromedriver.exe");
			driver = new ChromeDriver();
			log.debug("ChromeDriver created!!");
		}else if(config.getProperty("browser").equals("firefox")) {
			driver = new FirefoxDriver();
			log.debug("FirefoxDriver created!!");
		}else if(config.getProperty("browser").equals("ie")) {
			driver = new InternetExplorerDriver();
			log.debug("InternetExplorerDriver created!!");
		}
		
		
		driver.manage().window().maximize();
		driver.get(config.getProperty("testsiteurl"));
		log.debug("URL Opened!!");
		//Implicit wait only for checking presence of element. Not for alert.
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
		wait = new WebDriverWait(driver,5);
	}
	
	
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public void click(String Locator) {
		if(Locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(or.getProperty(Locator))).click();
		}else if(Locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(or.getProperty(Locator))).click();
		}else if(Locator.endsWith("_ID")) {
			driver.findElement(By.id(or.getProperty(Locator))).click();
		}
		
		test.log(LogStatus.INFO, "Clicking on : "+Locator);
	}
	
	public void type(String Locator, String value) {
		if(Locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(or.getProperty(Locator))).sendKeys(value);
		}else if(Locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(or.getProperty(Locator))).sendKeys(value);
		}else if(Locator.endsWith("_ID")) {
			driver.findElement(By.id(or.getProperty(Locator))).sendKeys(value);
		}
		
		test.log(LogStatus.INFO, "Typing value : "+value);
	}
	
	@AfterSuite
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
			log.debug("Driver Quitted!!");
		}
	}
	
	
}
