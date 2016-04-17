package com.kazaf.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.kazaf.plugins.RWExcel;

public class RWExcelTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, EncryptedDocumentException, InvalidFormatException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		// TODO Auto-generated method stub

		RWExcel rwetest=new RWExcel();
		
		String  file="/Users/kazaf/Desktop/2016.xlsx";
		
		String type="gym";
		
		int month=1;
		month=month-1;
		
		rwetest.readExcel5(file,month,type);
	}

}
