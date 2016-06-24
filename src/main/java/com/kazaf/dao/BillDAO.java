package com.kazaf.dao;

import com.kazaf.pojos.Bill;

import java.util.Date;
import java.util.List;
//test

public interface BillDAO {

	public Bill getBill(int bill_id);
	
	public void insertBill(Bill bill);

	public List<Bill> getAllBill();

	public int getconsumeDays(Date firstday,Date lastday);

	public List<Bill> getBillbyMonth(java.sql.Date startday, java.sql.Date enddate);

}
