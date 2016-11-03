package menu.program.printer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class MultiScanner {

	Scanner scanner = new Scanner(System.in);
	DateParser dateParser = new DateParser();

	public int nextInt() {
		return scanner.nextInt();
	}

	public String nextLine() {
		return scanner.nextLine();
	}

	public double nextDouble() {
		return scanner.nextDouble();
	}

	public Date parseDate(String stringDate) throws ParseException {
		return dateParser.parseDate(stringDate);
	}

	public Date parseTodayDate(){
		return dateParser.getToday();
	}
	
	public boolean hasNextInt(){
		return scanner.hasNextInt();
	}
	
	private class DateParser {

		private SimpleDateFormat format = new SimpleDateFormat();

		private Date parseDate(String stringDate) throws ParseException {

			format.applyPattern("dd.MM.yyyy");
			return format.parse(stringDate);
		}

		private Date getToday() {
			return Calendar.getInstance().getTime();
		}
	}
}
