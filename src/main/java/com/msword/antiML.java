package com.msword;

import com.msword.utils.ReadfromExcel;
import com.msword.utils.WritefromExcel;

/**
 * Created by Kazaf on 16/5/30.
 */
public class antiML {

    public static void main(String[] args){
        ReadfromExcel readfromExcel=new ReadfromExcel();

        WritefromExcel writefromExcel=new WritefromExcel();
        try{
            writefromExcel.WritefromList(readfromExcel.readcustomer("liuli.xlsx"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    }


