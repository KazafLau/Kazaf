package com.credit.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.credit.dao.billdao;
import com.credit.domain.Bill;
import com.credit.plugins.DateConvertor;
import com.credit.plugins.RWExcel;

public class MybatisTest {
	
	private static  Reader reader;
	private static SqlSessionFactory factory;
	
	static{
		try {
			reader=Resources.getResourceAsReader("conf.xml");
			factory=new SqlSessionFactoryBuilder().build(reader);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static SqlSessionFactory getSession(){
		return factory;
	}

	public static void main(String[] args) throws EncryptedDocumentException, FileNotFoundException, InvalidFormatException, IOException {
		// TODO Auto-generated method stub

		SqlSession session=factory.openSession();
		Bill billtemp=new Bill();
		DateConvertor dc=new DateConvertor();
		
		
		
		try{
			billdao tempdao=session.getMapper(billdao.class);
			
			RWExcel rwetest=new RWExcel();
			
			String  file="/Users/kazaf/招商银行信用卡.xlsx";
			
			List billlist=rwetest.readExcel5(file,14);
			
			for(int i=0;i<billlist.size();i++)
			{
			
				billtemp=(Bill) billlist.get(i);
				tempdao.insertBill(billtemp);
				session.commit();
				billtemp=new Bill();
			}
			
			
		}
		finally{
			session.close();
		}
	}

}
