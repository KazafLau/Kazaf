package com.kazaf.service.impl;

import com.kazaf.service.IDateCalculatorService;
import com.kazaf.service.JDBCMySql;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;


@Service("DateCalculatorService")
public class DateCalculatorServiceImpl implements IDateCalculatorService {

	private Calendar lastd ;
	private Calendar firstd ;
	private Date firstday;
	private Date lastday;
	private int days;
	private Date day=Calendar.getInstance().getTime();
	private int cosumedays;

	public void setCosumedays(int cosumedays) {
		this.cosumedays = cosumedays;
	}

	public Date getFirstday() {
		return firstday;
	}

	public Date getLastday() {
		return lastday;
	}

	public int getCosumedays() {
		return cosumedays;
	}

	public int getDays() {
		return days;
	}

	public Date getDay() {
		return day;
	}

	/**
	 * 查出当月的第一天和最后一天日期
	 */
	private void firsttolast(int month){
		month-=1;
		lastd = Calendar.getInstance();
		firstd = Calendar.getInstance();
		firstd.set(Calendar.MONTH, month);
		firstd.set(Calendar.DAY_OF_MONTH, 1);
		firstday=firstd.getTime();
		lastd.set(Calendar.MONTH, month+1);
		lastd.set(Calendar.DAY_OF_MONTH, 0);
		lastday=lastd.getTime();
	}

	/**
	 * 不输入月份的构造函数
	 */
	public DateCalculatorServiceImpl(){
		firsttolast(day.getMonth() + 1);
		days=daysBetween(day.getMonth()+1);
	}

	/**
	 * 输入月份的构造函数查询特定月的天数
	 */
	public DateCalculatorServiceImpl(int month){

		firsttolast(month);
		days=daysBetween(month);

		System.out.println(firstday);
		System.out.println(lastday);
		System.out.println(days);
		//System.out.println(day);
		
	}

	/**
	 * 被用来查询天数所调用
	 */
	private int daysBetween(int month) {
		month-= 1;

		firstd.setTime(firstday);
		lastd.setTime(lastday);

		  setTimeToMidnight(firstd);
		  setTimeToMidnight(lastd);
		  long intervalMs = lastd.getTimeInMillis() - firstd.getTimeInMillis();
		  return millisecondsToDays(intervalMs)+1;
		}

	private int millisecondsToDays(long intervalMs) {
		  return (int) (intervalMs / (1000 * 86400));
		}

	private void setTimeToMidnight(Calendar calendar) {
		  calendar.set(Calendar.HOUR_OF_DAY, 0);
		  calendar.set(Calendar.MINUTE, 0);
		  calendar.set(Calendar.SECOND, 0);
		}

	public static void main(String[] args) {

		DateCalculatorServiceImpl a=new DateCalculatorServiceImpl(3);

		java.sql.Date firstd=new java.sql.Date(a.getFirstday().getTime());
		java.sql.Date lastd=new java.sql.Date(a.getLastday().getTime());

		JDBCMySql jdbcMySql=new JDBCMySql();
		System.out.println(jdbcMySql.getconsumeDays(firstd,lastd));
	}

}
