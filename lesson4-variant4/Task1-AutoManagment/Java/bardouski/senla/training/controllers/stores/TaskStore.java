package bardouski.senla.training.controllers.stores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import bardouski.senla.training.model.Task;

public class TaskStore {

	private static final String DATE_PARSE_ERRORS = "Date parsed with errors.";

	private SimpleDateFormat format = new SimpleDateFormat();
	private Calendar calendar = Calendar.getInstance();

	public Task createTask(String toDo, String startDateYYYY_MM_DD, String completeDateYYYY_MM_DD, double price) {

		Date requestDate = calendar.getTime();
		Date startDate = null;
		Date completeDate = null;

		format.applyPattern("dd.MM.yyyy");

		try {
			startDate = format.parse(startDateYYYY_MM_DD);
			completeDate = format.parse(completeDateYYYY_MM_DD);
		} catch (ParseException e) {
			System.out.println(DATE_PARSE_ERRORS);
			return null;
		}

		Task task = new Task(toDo, requestDate, startDate, completeDate, price);

		return task;
	}

}
