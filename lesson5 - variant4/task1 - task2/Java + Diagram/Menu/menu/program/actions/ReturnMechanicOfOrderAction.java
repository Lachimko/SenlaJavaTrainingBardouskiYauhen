package menu.program.actions;

import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Order;
import menu.program.actions.parents.FacadeInputAction;

public class ReturnMechanicOfOrderAction extends FacadeInputAction {

	private static final String MESSAGE = "Order Id: ";

	public ReturnMechanicOfOrderAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		printer.print(MESSAGE);

		try {
			Order order = facade.getOrder(scanner.nextInt());
			printer.print(order.getMechanic());
		} catch (NoSuchObjectException e) {
			logger.error(e.getMessage());
			printer.printFail(e.getMessage());
		}
	}
}
