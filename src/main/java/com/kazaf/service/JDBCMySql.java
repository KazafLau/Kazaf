package com.kazaf.service;

import com.kazaf.pojos.Bill;

import java.sql.*;
import java.util.*;

/**
 * Created by Kazaf on 16/4/10.
 */
public class JDBCMySql {

    private static String username="root";
    private static String password="19881129";
    private static Connection con;
    private PreparedStatement pst;
    private  ResultSet rs;
    
    //private static final ThreadLocal<Connection> 	CONNECTION_HOLDER	= new  ThreadLocal<Connection>();
    
    /* 获取数据库链接 */
   /*public static Connection getConnection(){
	  Connection conn=CONNECTION_HOLDER.get();
	  if(conn==null){
		  try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/credit?useUnicode=true&amp;characterEncoding=UTF-8", username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			CONNECTION_HOLDER.set(conn);
			}
	  }
	   return conn;
   }
   */

   /*关闭数据库*/
  /* public static void closeConnection(){
	con=CONNECTION_HOLDER.get();
	if(con!=null){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			CONNECTION_HOLDER.remove();
		}
	}
  }
   */
  
    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        JDBCMySql.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        JDBCMySql.password = password;
    }

   static {
        con=connect(username,password);
    }


    public static Connection connect(String username,String password){
             if(con==null) {
                      try {
                             //Class.forName("com.mysql.cj.jdbc.Driver");
                              Class.forName("com.mysql.jdbc.Driver");
                               Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/credit?useUnicode=true&amp;characterEncoding=UTF-8", username, password);
                               return con;
                           } catch (Exception e) {
                               e.printStackTrace();
                               return null;
                           }
                   }
                else return con;
            }

    public List<Bill> getAllBill(){

        List<Bill> blist=new ArrayList<Bill>();
        try {
            pst = con.prepareStatement("SELECT * FROM bill");
            rs=pst.executeQuery();
            Bill temp;
            while(rs.next()){
                temp=new Bill();
                temp.setBill_comments(rs.getString("bill_comments"));
                temp.setBill_cost(rs.getFloat("bill_cost"));
                temp.setBill_date(rs.getDate("bill_date"));
                temp.setBill_id(rs.getInt("bill_id"));
                temp.setBill_level(rs.getInt("bill_level"));
                blist.add(temp);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return blist;

    }

    public List<Bill> getTypebetween(String startdate,String enddate){
        List<Bill> tlist=new ArrayList<>();
        try {
                pst = con.prepareStatement("SELECT * FROM bill WHERE bill_date BETWEEN ? AND ?");
                pst.setDate(1, DateConvertor.toDate(startdate));
                pst.setDate(2, DateConvertor.toDate(enddate));
                rs=pst.executeQuery();
                Bill temp;
                while(rs.next()){
                    temp=new Bill();
                    temp.setBill_comments(rs.getString("bill_comments"));
                    temp.setBill_cost(rs.getFloat("bill_cost"));
                    temp.setBill_date(rs.getDate("bill_date"));
                    temp.setBill_id(rs.getInt("bill_id"));
                    temp.setBill_level(rs.getInt("bill_level"));
                    tlist.add(temp);
                }
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return tlist;
    }

    // TODO Auto-generated catch block
    public int getconsumeDays(java.sql.Date firstday, java.sql.Date lastday){

        int days=0;
        try {
            pst = con.prepareStatement("SELECT COUNT  FROM bill WHERE bill_date BETWEEN ? AND ?");
            pst.setDate(1, firstday);
            pst.setDate(2, lastday);
            rs=pst.executeQuery();
            days=rs.getInt(0);

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            return days;
        }
    }
}
