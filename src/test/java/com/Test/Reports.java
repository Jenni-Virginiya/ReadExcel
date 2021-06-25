package com.Test;

//import com.Test.SendEmail;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.EmailException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reports {
	
	WebDriver driver;
	ExtentSparkReporter reporter;
	ExtentReports reports;
	ExtentTest logger;
	//SendEmail mail;

	@BeforeTest
	public void startTest() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		String path = System.getProperty("user.dir") + "/extent-reports/reports/report"+sdf.format(new Date())+".html";
		reporter = new ExtentSparkReporter(path);
		reports = new ExtentReports();
		reports.attachReporter(reporter);
		
		reports.setSystemInfo("HostName", "Local Host");
		reports.setSystemInfo("Environment", "Testing Env");
		reports.setSystemInfo("UserName", "Jennifer");
		
		reporter.config().setDocumentTitle("Execution Report");
		reporter.config().setReportName("Test Execution Report");
		reporter.config().setTheme(Theme.STANDARD);
		
	}

	@AfterTest
	public void endTest() {
		reports.flush();
	}

	@AfterMethod
	public void captureStatus(ITestResult result) throws EmailException {
		if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, "This test is Passed");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, "This test is Failed");			
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
		String imagePath = System.getProperty("user.dir")+"/extent-reports/capture/scn_"+sdf.format(new Date())+".png";		
		TakesScreenshot cap = (TakesScreenshot) driver;
		File file = cap.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File(imagePath));
			logger.addScreenCaptureFromPath(imagePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		//SendEmail.send_mail();
	}

	@Test(priority = 1)
	public void login() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\j.virginiya.victor\\Downloads\\Selenium\\BrowserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demowebshop.tricentis.com/");		
		logger = reports.createTest("Login Test");
		Assert.assertTrue(true);
	}
	
	@Test(priority = 2)
	public void logout() {
		logger = reports.createTest("Logout Test");
		Assert.assertTrue(true);
	}
}
