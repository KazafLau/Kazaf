package com.stock.utils;

import java.sql.Date;

/**
 * Created by kazaf on 16-6-1.
 */
public class GetDate {
    public int year,month,day;
    Date sdate;
    StringBuffer temp;

    public Date CellDate(int intdate){
        temp=new StringBuffer(intdate+"");
        year=Integer.parseInt(temp.substring(0,4));
        month=Integer.parseInt(temp.substring(4,6));
        day=Integer.parseInt(temp.substring(6,8));
        sdate=new Date(year-1900,month-1,day);
        return sdate;
    }

}
