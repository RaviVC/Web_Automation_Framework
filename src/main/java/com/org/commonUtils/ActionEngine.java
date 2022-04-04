package com.org.commonUtils;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.org.testBase.DriverFactory;
import com.org.testBase.ObjectsRepo;
import com.org.testBase.TestBase;

public class ActionEngine extends ObjectsRepo{

	// Custom method for sendkeys 
	public void sendKeys_action(WebElement element,String fieldName, String valueToBeSent) {
		try {
			element.sendKeys(valueToBeSent);
			test.log(Status.PASS, fieldName + " ==> " + valueToBeSent + " is entered");
		} catch (Exception e) {
			test.log(Status.FAIL, "value entered in field " + fieldName + " ==> is failed due to exception"+ e );
			e.printStackTrace();
		}
	}

	// Custom method for click 
	public void click_action(WebElement element,String fieldName) {
		try {
			element.click();
			test.log(Status.PASS, fieldName + " is clicked successfully");
		} catch (Exception e) {
			test.log(Status.FAIL, "unable to click on field " + fieldName + " ==> is failed due to exception"+ e );
			e.printStackTrace();
		}
	}

	// Custom method for clear field 
	public void clearEditField_action(WebElement element,String fieldName) {
		try {
			element.clear();
			test.log(Status.PASS, fieldName + " is cleared successfully");
		} catch (Exception e) {
			test.log(Status.FAIL, "unable to clear the field " + fieldName + " ==> is failed due to exception "+ e );
			e.printStackTrace();
		}
	}

	// Custom method for mouse hover 
	public void moveToElement_action(WebElement element,String fieldName) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor)DriverFactory.getInstance().getDriver();
			executor.executeScript("arguments[0].scrollIntoView(true)", element);
			Actions actions = new Actions(DriverFactory.getInstance().getDriver());
			actions.moveToElement(element).build().perform();
			test.log(Status.PASS, fieldName + " mouse hovered successfully");
		} catch (Exception e) {
			test.log(Status.FAIL, "unable hover mouse on field " + fieldName + " ==> is failed due to exception "+ e );
			e.printStackTrace();
		}
	}

	// Check if element is present 
	public boolean isElementDisplayed_action(WebElement element,String fieldName) {
		boolean flag = false;
		try {
			flag = element.isDisplayed();
			test.log(Status.PASS, fieldName + " ==> presence of field is "+ flag);
			return flag;
		} catch (Exception e) {
			test.log(Status.FAIL,  fieldName + "field is not present " + " ==>is failed due to exception "+ e );
			e.printStackTrace();
			return flag;
		}		
	}

	// Select dropdown value by visible text 
	public boolean selectDropdownByVisibleText_action(WebElement element,String fieldName, String ddVisibleText) {
		boolean flag = false;
		try {
			Select select = new Select(element);
			select.selectByVisibleText(ddVisibleText);
			test.log(Status.PASS, fieldName + " ==> dropdown value selected by visible text "+ ddVisibleText );
			return flag;
		} catch (Exception e) {
			test.log(Status.FAIL,  fieldName + "dropdown by visible text not selected " + " ==>is failed due to exception "+ e );
			e.printStackTrace();
			return flag;
		}		
	}

	// Select dropdown by value  
	public boolean selectDropdownByValue_action(WebElement element,String fieldName, String ddValue) {
		boolean flag = false;
		try {
			Select select = new Select(element);
			select.selectByVisibleText(ddValue);
			test.log(Status.PASS, fieldName + " ==> dropdown selected by value "+ ddValue );
			return flag;
		} catch (Exception e) {
			test.log(Status.FAIL,  fieldName + "dropdown by value not selected " + " ==>is failed due to exception "+ e );
			e.printStackTrace();
			return flag;
		}		
	}

	// String asserts  
	public void assertEqualsToString_action(String expValue,String actualValue, String locatorValue) {		
		try {
			Assert.assertEquals(expValue, actualValue);
			test.log(Status.PASS,"String assertion is successful on "+ locatorValue +"expvalue and actvalue is same");
		} catch (Exception e) {
			test.log(Status.FAIL, "String assertion is not successful on "+ locatorValue + " ==> and is failed due to exception "+ e );
			e.printStackTrace();
		}		
	}

	// Get text from webelement 
	public String getText_action(WebElement element,String fieldName) {		
		String text = "";
		try {
			text = element.getText();
			test.log(Status.PASS," ==> text retrieved successfully on "+ fieldName );
		} catch (Exception e) {
			test.log(Status.FAIL, "text not retrieved on "+ fieldName + " ==> and is failed due to exception "+ e );
			e.printStackTrace();
		}
		return text;		
	}



}
