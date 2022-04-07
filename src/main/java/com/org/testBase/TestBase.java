package com.org.testBase;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.org.commonUtils.ActionEngine;
import com.org.commonUtils.AppConfigManger;

public class TestBase extends ActionEngine {
		
	BrowserFactory bf = new BrowserFactory();
	
	@Parameters({"url","env"})
	@BeforeMethod
	public void LaunchApplication(String url,String env) throws Exception {
		String browser = AppConfigManger.getPropertyValueByKey("browser");
				
		String targetURL = null;
		
		if(env.equalsIgnoreCase("dev")) {
			targetURL = url.replaceAll(url, AppConfigManger.getPropertyValueByKey("dev_url"));
		}else if(env.equalsIgnoreCase("stg")){
			targetURL = url.replaceAll(url, AppConfigManger.getPropertyValueByKey("stg_url"));
		}else if(env.equalsIgnoreCase("uat")){
			targetURL = url.replaceAll(url, AppConfigManger.getPropertyValueByKey("uat_url"));
		}else if(env.equalsIgnoreCase("prod")){
			targetURL = url.replaceAll(url, AppConfigManger.getPropertyValueByKey("prod_url"));
		}
		
		DriverFactory.getInstance().setDriver(bf.createBrowserInstance(browser));
		driver = DriverFactory.getInstance().getDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();	
		driver.navigate().to(targetURL);
		
	}
	
	@AfterMethod
	public void TearDown() {
		DriverFactory.getInstance().closeBrowser();
	}

}
