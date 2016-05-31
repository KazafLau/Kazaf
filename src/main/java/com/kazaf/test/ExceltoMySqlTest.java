package com.kazaf.test;

import com.kazaf.utils.ExceltoMySql;

import java.util.Scanner;

/**
 * Created by Kazaf on 16/3/21.
 */
public class ExceltoMySqlTest {

    public static void main(String args[]){

        ExceltoMySql etm=new ExceltoMySql();

        String  file="test.xlsx";

        String type="bill";

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入月份：");
        int month= sc.nextInt();

        try{
        etm.insertList(type,month,file);

        }

        catch (Exception e){
            e.printStackTrace();
        }


    }
}
