package com.org.testcase;

import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.org.commonUtils.ExcelOperations;
import com.org.pageObjects.LoginPageObjects;
import com.org.testBase.DriverFactory;
import com.org.testBase.TestBase;

public class LoginTest extends TestBase{

	LoginPageObjects loginPageObjects = new LoginPageObjects(DriverFactory.getInstance().getDriver());
	ExcelOperations excel = new ExcelOperations("BG_TestData");

	@Test(dataProvider = "BG_TestData")
	public void login1(Object obj1) throws Exception {
		
		@SuppressWarnings("unchecked")
		HashMap<String,String>testData = (HashMap<String,String>)obj1;
		test.log(Status.PASS, "DSS App launched successfully");
		String title = driver.getTitle();
		Assert.assertEquals(title, "Sign In | Bluegreen Vacation Packages");
		System.out.println(testData);		
		loginPageObjects.launchApp(testData.get("UserName"),testData.get("Password"));
	}
	
	@DataProvider(name="BG_TestData")
	public Object[][] testDataSupplier() throws Exception{
		
		Object[][] obj = new Object[excel.getRowCount()][1];		
		for(int i=1;i<=excel.getRowCount();i++) {
			HashMap<String, String> testData = excel.getTestDataInMap(i);
			obj[i-1][0] = testData;
		}		
		return obj;	
	}
}
