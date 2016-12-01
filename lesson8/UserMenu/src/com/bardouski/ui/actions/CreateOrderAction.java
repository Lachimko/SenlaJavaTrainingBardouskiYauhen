package com.bardouski.ui.actions;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;

import com.bardouski.exceptions.EmptyCollectionException;
import com.bardouski.facade.IFacade;
import com.bardouski.model.IOrder;
import com.bardouski.model.ITask;
import com.bardouski.propertiesholder.PropertiesContext;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class CreateOrderAction extends FacadeInputAction{

	private static final String PARSED_WITH_ERRORS = "Date was parsed with errors.";

	public CreateOrderAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public String execute() {
		return null;

//		printer.print("Fill TODO field:");
//		String toDo = scanner.nextLine();
//		printer.print("Date, when order will start (dd.MM.yyyy):");
//		String startDate = scanner.nextLine();
//		printer.print("Date, when order will be completed (dd.MM.yyyy):");
//		String completeDate = scanner.nextLine();
//		printer.print("Price:");
//		double price = scanner.nextDouble();
//
//		try {
//			Task task;
//			try {
//				task = (Task) PropertiesContext.getInstance(ITask.class);
//				task.setToDo(toDo);
//				task.setRequestDate(scanner.parseTodayDate());
//				task.setStartDate(scanner.parseDate(startDate));
//				task.setCompleteDate(scanner.parseDate(completeDate));
//				task.setPrice(price);
//				Mechanic freeMechanic = facade.findFreeMechanic();
//				WorkPlace freeWorkPlace = facade.findFreePlace();
//
//				Order order = (Order) PropertiesContext.getInstance(IOrder.class);
//				order.setMechanic(freeMechanic);
//				order.setWorkPlace(freeWorkPlace);
//				order.setTask(task);
//			} catch (ClassNotFoundException e) {
//				logger.error(e.getMessage());
//			}
//
//		} catch (ParseException e) {
//			printer.printFail(PARSED_WITH_ERRORS);
//		} catch (EmptyCollectionException e) {
//			printer.printFail(e.getMessage());
//		}
	}

}
