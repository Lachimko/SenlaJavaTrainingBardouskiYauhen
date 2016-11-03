package menu.program.actions;

import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Order;
import bardouski.senla.training.model.enums.OrderStatus;
import menu.program.actions.parents.FacadeInputAction;

public class ActionReadyOrder extends FacadeInputAction{

	public ActionReadyOrder(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		
		try {
			Order order = facade.getOrder(scanner.nextInt());
			order.setOrderStatus(OrderStatus.READY);
		} catch (NoSuchObjectException e) {
			printer.printFail(e.getMessage());
		}
	}

}
