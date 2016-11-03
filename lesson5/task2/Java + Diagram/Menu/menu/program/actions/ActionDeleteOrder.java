package menu.program.actions;

import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Order;
import bardouski.senla.training.model.enums.OrderStatus;
import menu.program.actions.parents.FacadeInputAction;

public class ActionDeleteOrder extends FacadeInputAction {

	public ActionDeleteOrder(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		try {
			Order order = facade.getOrder(scanner.nextInt());
			order.setOrderStatus(OrderStatus.DELETED);
		} catch (NoSuchObjectException e) {
			printer.printFail(e.getMessage());
		}
	}
}
