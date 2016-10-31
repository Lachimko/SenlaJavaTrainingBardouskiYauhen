package menu.program.actions;

import java.util.List;

import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Order;
import menu.program.actions.parents.FacadeAction;

public class ActionReadyOrdersSortByRequestDate extends FacadeAction{

	public ActionReadyOrdersSortByRequestDate(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		List<Order> sortedOrders = facade.sortOrderByRequestDate();
		List<Order> readyOrders = facade.viewOrdersCompleted(sortedOrders);
		
		printer.print(readyOrders);
	}
}
