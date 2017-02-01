package com.bardouski.mysqlparser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Convert util parsing String contains date to MySql compatible format */
public class MySQLDateParser {

	private static final String DATE_PATTERN = "dd-MM-yyyy";
	private static final SimpleDateFormat format = new SimpleDateFormat();
	private static DateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);

	static {
		format.applyPattern(DATE_PATTERN);
	}

	public static final Date generateDateFromString(String dateString) {

		try {
			return format.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}

	public static final String generateStringFromDate(Date date) {
		return dateFormat.format(date);
	}

	private MySQLDateParser() {
	}

}
