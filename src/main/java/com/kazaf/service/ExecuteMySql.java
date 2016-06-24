package com.kazaf.service;

import com.kazaf.dao.CommonDAO;
import com.kazaf.pojos.Bill;
import com.kazaf.pojos.Gym;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by Kazaf on 16/4/2.
 */

@Repository
public class ExecuteMySql {

    private static Reader reader;
    private static SqlSessionFactory factory;
    private static SqlSession session;
    private static CommonDAO commonDao;
    private static List<Bill> BillList;
    private static List<Gym> GymList;
    private static ExecuteMySql em;

    static {
        try {
            reader = Resources.getResourceAsReader("conf.xml");
            factory = new SqlSessionFactoryBuilder().build(reader);
            em=new ExecuteMySql();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(BillList==null|GymList==null)
        {
        FirstInit();
        }

    }

    public ExecuteMySql() {
    }

    public static Reader getReader() {
        if(reader==null){
           try{ reader = org.apache.ibatis.io.Resources.getResourceAsReader("conf.xml");}
           catch (IOException e) {
               e.printStackTrace();
           }
        }
        return reader;
    }

    public static SqlSessionFactory getFactory() {
        if(factory==null){
            factory= new SqlSessionFactoryBuilder().build(reader);
        }
        return factory;
    }

    public static void sessionCommit(){
        getSession().commit();
    }

    public static SqlSession getSession() {
        if (session==null){
            session=getFactory().openSession();
        }
        return session;
    }

    public static CommonDAO getCommonDao() {
        session=getFactory().openSession();
        commonDao=session.getMapper(CommonDAO.class);
        return commonDao;
    }

    public static void setCommonDao(CommonDAO COmmonDao) {
        commonDao = COmmonDao;
    }

    public static void OpenMysql(){

        if(session==null)
        {
        session = factory.openSession();
        }
        commonDao=session.getMapper(CommonDAO.class);

    }

    public static void CloseMysql(){

        session.close();
    }

    private static void FirstInit(){
        BillList=getCommonDao().getAllBill();
       // GymList=getCommonDao().getAllGym();
    }

    public static  ExecuteMySql getInstance(){
        if(em==null){
            em=new ExecuteMySql();
        }
        return em;
    }

    public static List<Bill> getBillList() {
        return BillList;
    }

    public static void setBillList(List<Bill> billList) {
        BillList = billList;
    }

    public static List<Gym> getGymList() {
        return GymList;
    }

    public static void setGymList(List<Gym> gymList) {
        GymList = gymList;
    }
}
