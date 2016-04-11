package com.kazaf.domain;

import java.sql.Date;

public class Bill implements BGinter {
	
	private Date bill_date;
	private float bill_cost;
	private String bill_comments;
	private int bill_id;
	private int bill_level;

	public Bill() {
	}


	public int getBill_level() {
		return bill_level;
	}
	public void setBill_level(int bill_level) {
		this.bill_level = bill_level;
	}
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
	public String getBill_comments() {
		return bill_comments;
	}
	public void setBill_comments(String bill_coments) {
		this.bill_comments = bill_coments;
	}
	public int getBill_id() {
		return bill_id;
	}
	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}
	
	

}
