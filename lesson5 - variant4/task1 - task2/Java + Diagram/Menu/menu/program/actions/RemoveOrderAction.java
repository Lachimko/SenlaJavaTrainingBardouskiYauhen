package menu.program.actions;

import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Order;
import menu.program.actions.parents.FacadeInputAction;

public class RemoveOrderAction extends FacadeInputAction{

	private static final String ORDER_ID = "Order ID to delete:";

	public RemoveOrderAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		
		printer.print(ORDER_ID);
		try {
			Order order = facade.getOrder(scanner.nextInt());
			facade.removeOrder(order);
			printer.print("Order successfull removed");
		} catch (NoSuchObjectException e) {
			logger.error(e.getMessage());
			printer.printFail(e.getMessage());
		}
	}
}
