package com.stock.test;

import com.stock.utils.GetDate;

/**
 * Created by kazaf on 16-6-1.
 */
public class GetDateTest {
    public static void main(String[] args){
        GetDate getDate=new GetDate();
        System.out.println(getDate.CellDate(19881129));
        System.out.println(getDate.year+" "+getDate.month+" "+getDate.day+" ");
    }
}
