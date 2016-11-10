package com.bardouski.ui.actions;

import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.facade.Facade;
import com.bardouski.program.model.Order;
import com.bardouski.program.model.enums.OrderStatus;
import com.bardouski.ui.actions.parents.FacadeInputAction;

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
