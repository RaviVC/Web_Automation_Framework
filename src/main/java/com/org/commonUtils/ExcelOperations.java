package com.org.commonUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelOperations {

	String filePath;
	Sheet sh;

	public ExcelOperations(String sheetName){
		try {
			filePath = System.getProperty("user.dir")+AppConfigManger.getPropertyValueByKey("testLocation");
			File testDataFile = new File(filePath);
			Workbook wb = WorkbookFactory.create(testDataFile);
			sh = wb.getSheet(sheetName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}



	public HashMap<String, String> getTestDataInMap(int rowNum) throws Exception {

		// Get the excel location 
		// Open the excel workbook 
		// Read test data row by row and put in HashMap 

		// read the data and put it in HashMap 
		HashMap<String,String> hm = new HashMap<String, String>();

		for(int i = 0;i<sh.getRow(0).getLastCellNum();i++) {
			String value;
			if(sh.getRow(rowNum).getCell(i) != null) {
				sh.getRow(rowNum).getCell(i).setCellType(CellType.STRING);
				value = sh.getRow(rowNum).getCell(i).toString();
			}else {
				value = "";
			}
			hm.put(sh.getRow(0).getCell(i).toString(),value);
		}

		return hm;
	}
	
	public int getRowCount() {
		return sh.getLastRowNum();		
	}
	
	public int getColCount() {
		return sh.getRow(0).getLastCellNum();
	}
}
