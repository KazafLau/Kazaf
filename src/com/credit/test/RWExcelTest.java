package com.credit.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.credit.plugins.RWExcel;

public class RWExcelTest {

	public static void main(String[] args) throws FileNotFoundException, IOException, EncryptedDocumentException, InvalidFormatException {
		// TODO Auto-generated method stub

		RWExcel rwetest=new RWExcel();
		
		String  file="/Users/kazaf/招商银行信用卡.xlsx";
		
		rwetest.readExcel5(file,14);
	}

}
