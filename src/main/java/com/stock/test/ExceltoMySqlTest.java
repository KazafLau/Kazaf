package com.stock.test;

import com.stock.utils.ExceltoMySql;

import java.util.Scanner;

/**
 * Created by Kazaf on 16/3/21.
 */
public class ExceltoMySqlTest {

    public static void main(String args[]){

        ExceltoMySql etm=new ExceltoMySql();

        String  file="cwl.xlsx";




        try{
        etm.insertList(file);

        }

        catch (Exception e){
            e.printStackTrace();
        }


    }
}
