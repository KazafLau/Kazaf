package com.kazaf.test;

import com.kazaf.service.ExceltoMySql;

import java.util.Scanner;

/**
 * Created by Kazaf on 16/3/21.
 */
public class ExceltoMySqlTest {

    public static void main(String args[]){

        ExceltoMySql etm=new ExceltoMySql();

        String  file="/Users/kazaf/Documents/workspace/Bill/out/artifacts/Bill_war_exploded/WEB-INF/upload/2/7/d44e8e1c-039d-48a1-91da-13c42cf22e9a_test.xlsx";

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
