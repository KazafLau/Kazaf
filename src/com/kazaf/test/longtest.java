package com.kazaf.test;

import com.kazaf.domain.Bill;
import com.kazaf.domain.Gym_Anaerobic;
import com.kazaf.plugins.ExecuteMySql;
import com.kazaf.plugins.JDBCMySql;
import com.kazaf.service.BMRcalculator;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;


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

        //System.out.println(ExecuteMySql.getBillList());

       // System.out.println(ExecuteMySql.getBillList().get(0).getBill_date() + " " + ExecuteMySql.getBillList().get(0).getBill_comments());

       // List<Bill> nList= ExecuteMySql.getBillList();

        //nList.remove(0);


        //ExecuteMySql.setBillList(nList);

       // System.out.println(ExecuteMySql.getBillList().get(0).getBill_date() + " " + ExecuteMySql.getBillList().get(0).getBill_comments());

       // System.out.println(ExecuteMySql.t1);

       // em.t1=2;

       // System.out.println(ExecuteMySql.t1);


        JDBCMySql jdbcMySql=new JDBCMySql();
        String startdate="2015-01-01";
        String enddate="2016-03-31";
        for(Bill bill:jdbcMySql.getTypebetween(startdate,enddate)){
            System.out.println(bill.getBill_date()+" "+bill.getBill_comments()+" "+bill.getBill_cost());
        }



    }
}
