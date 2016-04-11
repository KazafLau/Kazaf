package com.kazaf.dao;

import com.kazaf.domain.Bill;

import java.util.List;

public interface billdao {

	public Bill getBill(int bill_id);
	
	public void insertBill(Bill bill);

	public List<Bill> getAllBill();

}
