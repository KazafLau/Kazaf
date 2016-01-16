package com.credit.plugins;
import java.sql.Date;
import java.text.SimpleDateFormat;


public class DateConvertor {
	
	SimpleDateFormat sdf=new SimpleDateFormat( "yyyy-MM-dd"); 
	

	public Date toDate(String sDate){
			
		java.sql.Date dDate=java.sql.Date.valueOf(sDate); 
	    return dDate;
			
	}
	
	public String toString(Date dDate){
		
	  String sDate=sdf.format(dDate);
	  return sDate;
		
	}
}
