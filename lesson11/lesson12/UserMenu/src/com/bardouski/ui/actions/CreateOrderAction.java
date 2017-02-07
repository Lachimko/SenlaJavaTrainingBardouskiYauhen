package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;

import com.bardouski.model.IMechanic;
import com.bardouski.model.IOrder;
import com.bardouski.model.ITask;
import com.bardouski.model.IWorkPlace;
import com.bardouski.propertiesholder.PropertiesContext;
import com.bardouski.requests.Request;
import com.bardouski.responses.Response;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class CreateOrderAction extends FacadeInputAction {

	private static final String PARSED_WITH_ERRORS = "Date was parsed with errors.";

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

		try {
			ITask task;
			task = (ITask) PropertiesContext.getInstance(ITask.class);
			task.setToDo(toDo);
			task.setRequestDate(scanner.parseTodayDate());
			task.setStartDate(scanner.parseDate(startDate));
			task.setCompleteDate(scanner.parseDate(completeDate));
			task.setPrice(price);

			IMechanic freeMechanic = null;
			IWorkPlace freeWorkPlace = null;

			out.writeObject(new Request("findFreeMechanic"));
			Response response;

			while ((response = (Response) in.readObject()) != null) {
				freeMechanic = (IMechanic) response.getResponceResult();
				break;
			}
			
			out.writeObject(new Request("findFreePlace"));
			while ((response = (Response) in.readObject()) != null) {
				freeWorkPlace = (IWorkPlace) response.getResponceResult();
				break;
			}
			
			IOrder order = (IOrder) PropertiesContext.getInstance(IOrder.class);
			order.setMechanic(freeMechanic);
			order.setWorkPlace(freeWorkPlace);
			order.setTask(task);

		} catch (ParseException e) {
			printer.printFail(PARSED_WITH_ERRORS);
		} catch (ClassNotFoundException | IOException e) {
			logger.error(e.getMessage());
		} 
	}

}
