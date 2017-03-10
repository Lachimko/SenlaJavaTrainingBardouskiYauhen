package com.bardouski.ui.scanner;

import java.text.ParseException;
import java.util.Date;

public interface IScanner {

	int nextInt();

	String nextLine();

	double nextDouble();

	Date parseTodayDate();

	boolean hasNextInt();

	Date parseDate(String stringDate) throws ParseException;

}
