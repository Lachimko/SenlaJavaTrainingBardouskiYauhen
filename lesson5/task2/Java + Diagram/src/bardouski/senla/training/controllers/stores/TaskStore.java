package bardouski.senla.training.controllers.stores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import bardouski.senla.training.controllers.stores.storeparent.Store;
import bardouski.senla.training.model.Task;

public class TaskStore extends Store {

	private SimpleDateFormat format = new SimpleDateFormat();
	private Calendar calendar = Calendar.getInstance();

	public Task createTask(String toDo, String startDate, String completeDate, double price) throws ParseException {

		Date requestDate = calendar.getTime();
		Date start = null;
		Date complete = null;

		format.applyPattern("dd.MM.yyyy");

			start = format.parse(startDate);
			complete = format.parse(completeDate);

		Task task = new Task(toDo, requestDate, start, complete, price);

		return task;
	}

}
