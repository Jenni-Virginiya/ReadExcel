package com.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class SwitchWindow {
  @Test
  public void MouseAction() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\j.virginiya.victor\\Downloads\\Selenium\\BrowserDriver\\chromedriver.exe");	  
	  WebDriver driver = new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  driver.get("https://lkmdemoaut.accenture.com/TestMeApp/fetchcat.htm");
	  
	  Actions act = new Actions(driver);
	  WebElement e1 = driver.findElement(By.xpath("//span[contains(text(),'AboutUs')]"));
	  act.moveToElement(e1).perform();
	  WebElement e2 = driver.findElement(By.xpath("//span[contains(text(),'Our')]"));
	  act.moveToElement(e2).perform();
	  WebElement e3 = driver.findElement(By.xpath("//span[contains(text(),'Chennai')]"));
	  act.moveToElement(e3).click().perform();
	  	  
	  Set <String> addr = driver.getWindowHandles();
	  for (String s : addr)
	  {
		driver.switchTo().window(s);  
	  }
	  driver.switchTo().frame(1);
	  String straddr = driver.findElement(By.id("demo3")).getText();
	  System.out.println("Address : "+ straddr);
	  
	  driver.close();	  	  
  }
}
