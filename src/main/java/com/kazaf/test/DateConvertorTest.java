

package com.kazaf.test;

import com.kazaf.service.DateConvertor;

public class DateConvertorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DateConvertor DCtest=new DateConvertor();
		
		String SDateTest="2015-11-22";
		
		System.out.println(DCtest.toDate(SDateTest));

		
		

	}

}
