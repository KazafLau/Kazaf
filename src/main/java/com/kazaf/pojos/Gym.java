package com.kazaf.pojos;
import java.sql.Date;

public class Gym implements BGinter {
	
	private Date gym_date;
	private int gym_id;
	private String gym_comments;
	private String gym_item;

	public Gym() {
	}





	public Date getGym_date() {
		return gym_date;
	}
	public void setGym_date(Date gym_date) {
		this.gym_date = gym_date;
	}
	public int getGym_id() {
		return gym_id;
	}
	public void setGym_id(int gym_id) {
		this.gym_id = gym_id;
	}
	public String getGym_comments() {
		return gym_comments;
	}
	public void setGym_comments(String gym_comments) {
		this.gym_comments = gym_comments;
	}
	public String getGym_item() {
		return gym_item;
	}
	public void setGym_item(String gym_item) {
		this.gym_item = gym_item;
	}
	

}
