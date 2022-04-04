package com.org.commonUtils;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class AppConfigManger {

	static Properties prop = new Properties();

	public static String getPropertyValueByKey(String key) throws Exception {

		//1. Load data from properties file
		String propFilePath = System.getProperty("user.dir")+"/src/test/resources/config.properties";
		FileInputStream fis = new FileInputStream(propFilePath);
		prop.load(fis);

		//2. Read data 
		String value = prop.get(key).toString();
		if(StringUtils.isEmpty(value)) {
			throw new Exception("Value is not specified for key"+ key + "in properties file");

		}

		return value;
	}

}
