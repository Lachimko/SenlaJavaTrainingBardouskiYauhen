package menu.program.actions;

import java.text.ParseException;

import bardouski.senla.training.exceptions.EmptyCollectionException;
import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Mechanic;
import bardouski.senla.training.model.Order;
import bardouski.senla.training.model.Task;
import bardouski.senla.training.model.WorkPlace;
import menu.program.actions.parents.FacadeInputAction;

public class ActionCreateOrder extends FacadeInputAction{

	private static final String PARSED_WITH_ERRORS = "Date was parsed with errors.";

	public ActionCreateOrder(Facade facade) {
		super(facade);
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
		double price = scanner.nextDouble();
		
		try {
			Task task = new Task(toDo, scanner.parseTodayDate(), scanner.parseDate(startDate), scanner.parseDate(completeDate), price);
			Mechanic freeMechanic = facade.findFreeMechanic();
			WorkPlace freeWorkPlace = facade.findFreePlace();
			
			Order order = new Order(freeMechanic, freeWorkPlace, task);
			
			facade.addOrder(order);
			
		} catch (ParseException | EmptyCollectionException e) {
			if (e instanceof ParseException){
				printer.print(PARSED_WITH_ERRORS);
			} else {
				printer.print(e.getMessage());
			}
		}
	}

}
