package menu.program.actions;

import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Order;
import bardouski.senla.training.model.enums.OrderStatus;
import menu.program.actions.parents.FacadeInputAction;

public class CancelOrderAction extends FacadeInputAction{

	private static final String ORDER_ID = "Order Id to cancel:";

	public CancelOrderAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		printer.print(ORDER_ID);
		try {
			Order order = facade.getOrder(scanner.nextInt());
			order.setOrderStatus(OrderStatus.CANCELLED);
		} catch (NoSuchObjectException e) {
			logger.error(e.getMessage());
			printer.printFail(e.getMessage());
		}
	}
}
