package com.kazaf.test;

import com.kazaf.pojos.Bill;
import com.kazaf.service.JDBCMySql;


/**
 * Created by Kazaf on 16/3/23.
 */
public class longtest {

    public static void main(String args[]){

      // ExecuteMySql em=new ExecuteMySql();
       // ExecuteMySql em1=new ExecuteMySql();

       // em.OpenMysql();
       // em1.OpenMysql();

       // System.out.println(em.getCommonDao());
       // System.out.println(em1.getCommonDao());
       // System.out.println(ExecuteMySql.getCommonDao());


        //ExecuteMySql em=new ExecuteMySql();

       // System.out.println(ExecuteMySql.getBillList());

       // System.out.println(ExecuteMySql.getBillList().get(0).getBill_date() + " " + ExecuteMySql.getBillList().get(0).getBill_comments());

       // List<Bill> nList= ExecuteMySql.getBillList();

        //nList.remove(0);


        //ExecuteMySql.setBillList(nList);

       // System.out.println(ExecuteMySql.getBillList().get(0).getBill_date() + " " + ExecuteMySql.getBillList().get(0).getBill_comments());

       // System.out.println(ExecuteMySql.t1);

       // em.t1=2;

       // System.out.println(ExecuteMySql.t1);


    	//System.out.println("THis is before  "+JDBCMySql.getConnection());




        System.out.println(java.nio.charset.Charset.defaultCharset() );
        JDBCMySql jdbcMySql=new JDBCMySql();
      //System.out.println("THis is after  "+JDBCMySql.getConnection());
        String startdate="2015-01-01";
        String enddate="2016-03-31";
        for(Bill bill:jdbcMySql.getTypebetween(startdate,enddate)){
            System.out.println(bill.getBill_date()+" "+bill.getBill_comments()+" "+bill.getBill_cost());
        }



    }
}
