package com.credit.domain;

import java.sql.Date;

public class Bill {
	
	public Date bill_date;
	public float bill_cost;
	public String bill_coments;
	public int bill_id;
	public Date getBill_date() {
		return bill_date;
	}
	public void setBill_date(Date bill_date) {
		this.bill_date = bill_date;
	}
	public float getBill_cost() {
		return bill_cost;
	}
	public void setBill_cost(float bill_cost) {
		this.bill_cost = bill_cost;
	}
	public String getBill_coments() {
		return bill_coments;
	}
	public void setBill_coments(String bill_coments) {
		this.bill_coments = bill_coments;
	}
	public int getBill_id() {
		return bill_id;
	}
	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}
	
	

}
