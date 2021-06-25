package com.Page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
	
	@FindBy(how=How.ID, using="Email")
	public static WebElement Email;
	@FindBy(how=How.XPATH, using="//input[@id='Password']")
	public static WebElement Password;
	@FindBy(how=How.CSS, using="input[value='Log in']")
	public static WebElement Submit;
	@FindBy(how=How.CLASS_NAME, using="account")
	public static WebElement AccountID;
	@FindBy(linkText = "Log out")
	public static WebElement logout;
	
}
