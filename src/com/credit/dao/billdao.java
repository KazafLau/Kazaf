package com.credit.dao;

import com.credit.domain.Bill;

public interface billdao {

	public Bill getBill(int bill_id);
	
	public void insertBill(Bill bill);
}
