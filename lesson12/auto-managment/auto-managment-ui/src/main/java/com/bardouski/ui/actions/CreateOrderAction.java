package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;

import com.bardouski.model.ITask;
import com.bardouski.propertiesholder.PropertiesContext;
import com.bardouski.requests.Request;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class CreateOrderAction extends FacadeInputAction {

	public CreateOrderAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public void execute() {

		printer.print("Fill TODO field:");
		String toDo = scanner.nextLine();
		printer.print("Date, when order will start (dd.MM.yyyy):");
		String startDate = scanner.nextLine();
		printer.print("Date, when order will be completed (dd.MM.yyyy):");
		String completeDate = scanner.nextLine();
		printer.print("Price:");
		Double price = scanner.nextDouble();

		ITask task;
		try {
			task = (ITask) PropertiesContext.getInstance(ITask.class);
			task.setToDo(toDo);
			task.setRequestDate(scanner.parseTodayDate());
			task.setStartDate(scanner.parseDate(startDate));
			task.setCompleteDate(scanner.parseDate(completeDate));
			task.setPrice(price);

			out.writeObject(new Request("addOrder", task));
		} catch (ClassNotFoundException | ParseException | IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
	}
}
