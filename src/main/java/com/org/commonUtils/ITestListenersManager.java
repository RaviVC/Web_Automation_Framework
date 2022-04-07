package com.org.commonUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.org.commonUtils.ExtentReportManager;
import com.org.testBase.ObjectsRepo;

public class ITestListenersManager extends ObjectsRepo implements ITestListener {

	public void onTestStart(ITestResult result) {
		//Before each test case 
		test = extent.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Case: "+ result.getMethod().getMethodName()+ "is passed");
	}
	
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test Case: "+ result.getMethod().getMethodName()+ "is failed");
		test.log(Status.FAIL, result.getThrowable());		
		attachScreenshot();
	}

	public void onTestSkipped(ITestResult result) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		//Setup extent 
		extent = ExtentReportManager.setupExtentReport();
	}

	public void onFinish(ITestContext context) {
		//Close extent 
		extent.flush();
	}
	
	public void attachScreenshot() {
		
		//Add screenshot for failed test cases
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date = new Date();
		String actualDate = format.format(date);
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String screenShotPath = System.getProperty("user.dir")+"/Reports/Screenshots/"+ actualDate + ".jpeg";
		File dest = new File(screenShotPath);
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			test.addScreenCaptureFromPath(screenShotPath, " test case failure screenshot");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
