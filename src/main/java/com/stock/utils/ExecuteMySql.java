package com.stock.utils;

import com.kazaf.dao.CommonDAO;
import com.kazaf.pojos.Bill;
import com.kazaf.pojos.Gym;
import com.stock.dao.CWLDAO;
import com.stock.pojos.TradeInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by Kazaf on 16/4/2.
 */
public class ExecuteMySql {

    private static Reader reader;
    private static SqlSessionFactory factory;
    private static SqlSession session;
    private static CWLDAO cwlDao;
    private static List<TradeInfo> CWLList;
    private static ExecuteMySql em;

    static {
        try {
            reader = Resources.getResourceAsReader("confCWL.xml");
            factory = new SqlSessionFactoryBuilder().build(reader);
            em=new ExecuteMySql();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public ExecuteMySql() {
    }

    public static Reader getReader() {
        if(reader==null){
           try{ reader = Resources.getResourceAsReader("confCWL.xml");}
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

    public static CWLDAO getCWLDao() {
        session=getFactory().openSession();
        cwlDao=session.getMapper(CWLDAO.class);
        return cwlDao;
    }

    public static void setCommonDao(CWLDAO CWLDao) {
        cwlDao = CWLDao;
    }

    public static void OpenMysql(){

        if(session==null)
        {
        session = factory.openSession();
        }
        cwlDao=session.getMapper(CWLDAO.class);

    }

    public static void CloseMysql(){

        session.close();
    }



    public static ExecuteMySql getInstance(){
        if(em==null){
            em=new ExecuteMySql();
        }
        return em;
    }

    public static List<TradeInfo> getBillList() {
        return CWLList;
    }

    public static void setBillList(List<TradeInfo> CWLlist) {
        CWLList = CWLlist;
    }

}
