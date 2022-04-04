package com.org.pageObjects;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.org.commonUtils.ActionEngine;
import com.org.testBase.DriverFactory;
import com.org.testBase.TestBase;

public class LoginPageObjects extends TestBase {
	
	//ActionEngine actionEngine = new ActionEngine();

	//Initialize all page objects for given driver instance 
	public LoginPageObjects(WebDriver driver) {
		PageFactory.initElements(driver, this);		
	}

	By EMAIL = By.id("UserName");
	By PASSWORD = By.id("Password");
	By SIGNINBTN = By.id("signin_btn");
		
	//Launch the application 
	public void launchApp(String username, String password) {
		sendKeys_action(DriverFactory.getInstance().getDriver().findElement(EMAIL), "UsernameFld",username);
		sendKeys_action(DriverFactory.getInstance().getDriver().findElement(PASSWORD), "PasswordFld", password);
		click_action(DriverFactory.getInstance().getDriver().findElement(SIGNINBTN), "Sign In Btn");
	}
}

