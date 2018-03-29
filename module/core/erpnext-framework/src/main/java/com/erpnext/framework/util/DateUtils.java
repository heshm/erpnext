package com.erpnext.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static Date stringToDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}
	
	/*public static void main(String ...strings) {
		System.out.println(DateUtils.stringToDate("2018-04-01").toLocaleString());
	}*/

}
