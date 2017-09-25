package com.codeworks.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateUtils;

public class DateHelper {
		
	static {
		System.setProperty("user.timezone", "UTC");
		//System.setProperty("user.timezone", "EST");
		TimeZone.setDefault(null);
	}
	
	private static Calendar CALENDAR = new GregorianCalendar(TimeZone.getDefault());
	private static SimpleDateFormat MMDDYYYYFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	public static Date getCurrentDateInUTC(){
		return CALENDAR.getTime();
	}
	
	public static String dateInMMDDYYYYFormat(Date date){
		return MMDDYYYYFormat.format(date);		
	}
	
	public static Date dateInMMDDYYYYFormat(String formattedDate) throws ParseException{
		return MMDDYYYYFormat.parse(formattedDate);		
	}
	
	public static Date createTargetDate(Date date, int numberOfDays){
		return DateUtils.addDays(date, numberOfDays);
	}
}
