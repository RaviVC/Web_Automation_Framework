package com.org.testBase;

import org.openqa.selenium.WebDriver;

public class DriverFactory {
	
	// Singleton design pattern - only one instance exist forever , provide global access 
	// to that instance by creating get instance method
	// Factory design pattern 
	
	private DriverFactory() {

	}

	private static DriverFactory instance = new DriverFactory();

	public static DriverFactory getInstance() {
		return instance;
	}

	//Factory design pattern -- define seperate factory methods for creating objects 
	//and create objects by calling methods

	ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public WebDriver getDriver() {
		return driver.get();
	}

	public void setDriver(WebDriver driverParam) {
		driver.set(driverParam);
	}

	public void closeBrowser() {
		driver.get().close();
		driver.remove();
	}

}
