

package com.credit.test;

import java.sql.Date;

import com.credit.plugins.DateConvertor;

public class DateConvertorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DateConvertor DCtest=new DateConvertor();
		
		String SDateTest="2015-11-22";
		
		System.out.println(DCtest.toDate(SDateTest));

		
		

	}

}
