package com.kazaf.plugins;
import java.sql.Date;
import java.text.SimpleDateFormat;


public class DateConvertor {
	
	private static  SimpleDateFormat sdf=new SimpleDateFormat( "yyyy-MM-dd");
	

	public static Date toDate(String sDate){
			
		java.sql.Date dDate=java.sql.Date.valueOf(sDate); 
	    return dDate;
			
	}
	
	public static String toString(Date dDate){
		
	  String sDate=sdf.format(dDate);
	  return sDate;
		
	}
}
