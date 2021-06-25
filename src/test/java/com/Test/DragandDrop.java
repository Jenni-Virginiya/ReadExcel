package com.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragandDrop {
	@Test
	public void dragpackage() throws Exception {
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\j.virginiya.victor\\Downloads\\Selenium\\BrowserDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demos.telerik.com/aspnet-ajax/treeview/examples/overview/defaultcs.aspx");
			
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");		
		Thread.sleep(4000);
		
		WebElement src = driver.findElement(By.xpath("//li[@class='rtLI rtFirst rtLast']//ul//li[3]//ul//span[contains(text(),'Weekend Package')]"));
		WebElement dest = driver.findElement(By.xpath("//td[@colspan='2']"));

		Actions act = new Actions(driver);
		act.dragAndDrop(src, dest).build().perform();
		Thread.sleep(4000);
		
		String strFinalAmount = driver.findElement(By.xpath("//td[contains(text(),'Subtotal')]//following-sibling::td")).getText();
		System.out.println(strFinalAmount);
		if (strFinalAmount.equals("$3999")) {
			Assert.assertTrue(true);
			System.out.println("Test Passed");
		} else {
			Assert.assertTrue(false);
			System.out.println("Test Failed");
		}
		
		driver.close();
	}
}
