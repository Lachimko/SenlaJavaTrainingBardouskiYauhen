package com.bardouski.ui.actions;

import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.facade.IFacade;
import com.bardouski.program.model.Order;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class ReturnMechanicOfOrderAction extends FacadeInputAction {

	private static final String MESSAGE = "Order Id: ";

	public ReturnMechanicOfOrderAction(IFacade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		printer.print(MESSAGE);

		try {
			Order order = facade.getOrder(scanner.nextInt());
			printer.print(order.getMechanic());
		} catch (NoSuchObjectException e) {
			printer.printFail(e.getMessage());
		}
	}
}
