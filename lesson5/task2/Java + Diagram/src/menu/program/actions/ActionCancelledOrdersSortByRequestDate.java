package menu.program.actions;

import java.util.List;

import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Order;
import menu.program.actions.parents.FacadeAction;

public class ActionCancelledOrdersSortByRequestDate extends FacadeAction{

	public ActionCancelledOrdersSortByRequestDate(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		List<Order> sortedOrders = facade.sortOrderByRequestDate();
		List<Order> readyOrders = facade.viewOrdersCancelled(sortedOrders);
		
		printer.print(readyOrders);
	}
}
