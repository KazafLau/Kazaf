package com.stock.utils;

import java.sql.Date;

/**
 * Created by kazaf on 16-6-1.
 */
public class GetDate {
    int year,month,day;
    Date sdate;
    StringBuffer temp;

    public Date CellDate(int intdate){
        temp=new StringBuffer(intdate+"");
        year=Integer.parseInt(temp.substring(0,4));
        month=Integer.parseInt(temp.substring(4,6));
        day=Integer.parseInt(temp.substring(6,8));
        sdate=new Date(year,month,day);
        return sdate;
    }


    public static void main(String[] args){
        GetDate getDate=new GetDate();
        System.out.println(getDate.CellDate(20160528));
        System.out.println(getDate.year+" "+getDate.month+" "+getDate.day+" ");
    }
}
