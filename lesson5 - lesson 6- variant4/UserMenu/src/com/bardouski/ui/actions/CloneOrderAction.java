package com.bardouski.ui.actions;

import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.facade.Facade;
import com.bardouski.program.model.Order;
import com.bardouski.ui.actions.parents.FacadeInputAction;

/**
 * Action generate the clone of chosen instance of Order. Change id and add to
 * OrderStore collection
 */
public class CloneOrderAction extends FacadeInputAction {

	private static final String INPUT_ORDER_ID = "Order Id to clone:";

	public CloneOrderAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		printer.print(INPUT_ORDER_ID);
		try {

			Order order = facade.getOrder(scanner.nextInt());
			Order clonedOrder = (Order) order.clone();
			clonedOrder.setId(facade.getNextOrderId());
			facade.addOrder(clonedOrder);

		} catch (NoSuchObjectException e) {
			printer.printFail(e.getMessage());
		} catch (CloneNotSupportedException e) {
			printer.printFail(e.getMessage());
			logger.error(e.getMessage());
		}
	}

}
