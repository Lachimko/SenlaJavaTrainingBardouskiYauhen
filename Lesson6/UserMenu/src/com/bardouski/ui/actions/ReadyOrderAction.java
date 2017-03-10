package com.bardouski.ui.actions;

import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.facade.Facade;
import com.bardouski.program.model.Order;
import com.bardouski.program.model.enums.OrderStatus;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class ReadyOrderAction extends FacadeInputAction{

	private static final String ORDER_ID = "Order id to make ready:";

	public ReadyOrderAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		
		printer.print(ORDER_ID);
		try {
			Order order = facade.getOrder(scanner.nextInt());
			order.setOrderStatus(OrderStatus.READY);
		} catch (NoSuchObjectException e) {
			printer.printFail(e.getMessage());
		}
	}

}
