package com.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Page.HomePage;
import com.Page.LoginPage;

public class ReadExcelData {
	
	WebDriver driver;
	
	@BeforeMethod
	public void openBrowser() {
		  System.setProperty("webdriver.chrome.driver", "C:\\Users\\j.virginiya.victor\\Downloads\\Selenium\\BrowserDriver\\chromedriver.exe");	  
		  driver = new ChromeDriver();
		  
		  PageFactory.initElements(driver, HomePage.class);
		  PageFactory.initElements(driver, LoginPage.class);
		  
		  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		  driver.manage().window().maximize();
		  driver.get("http://demowebshop.tricentis.com/");
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}
	
	public String login(String strEmail, String strPwd) {
		HomePage.Login.click();
		LoginPage.Email.sendKeys(strEmail);
		LoginPage.Password.sendKeys(strPwd);
		LoginPage.Submit.click();
		String strSystemMail = LoginPage.AccountID.getText();
		
		
//		driver.findElement(By.linkText("Log in")).click();
//		driver.findElement(By.id("Email")).sendKeys(strEmail);
//		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(strPwd);
//		driver.findElement(By.cssSelector("input[value='Log in']")).click();
//		String strSystemMail = driver.findElement(By.className("account")).getText();
		
		if (strSystemMail.equalsIgnoreCase(strEmail))		
		{
			LoginPage.logout.click();
//			driver.findElement(By.linkText("Log out")).click();
			return "Valid UserID";
		}
		else
		{
			return "Invalid UserID";
		}
	}

	@Test
	public void readExcelvalue()
	{
		String filepath = "C:\\Users\\j.virginiya.victor\\OneDrive - Accenture\\Desktop\\TestData.xlsx";
		File file = new File(filepath);
		try {
			InputStream is = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(is);
			XSSFSheet ws = wb.getSheet("Sheet1");
			
			int iLastRowNo = ws.getLastRowNum();
			
			for (int i=1 ; i <= iLastRowNo ; i++)
			{
				String strEmail = ws.getRow(i).getCell(0).getStringCellValue();
				String strPwd = ws.getRow(i).getCell(1).getStringCellValue();
				String strResult = login(strEmail,strPwd);
				ws.getRow(i).createCell(2).setCellValue(strResult);
			}
			
			OutputStream os = new FileOutputStream(file);
			wb.write(os);			
			wb.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
