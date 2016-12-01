package com.bardouski.ui.actions;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.NoSuchObjectException;

import com.bardouski.facade.IFacade;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class ReturnMechanicOfOrderAction extends FacadeInputAction {

	private static final String MESSAGE = "Order Id: ";

	public ReturnMechanicOfOrderAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public String execute() {
		return null;

//		printer.print(MESSAGE);
//
//		try {
//			Order order = facade.getOrder(scanner.nextInt());
//			printer.print(order.getMechanic());
//		} catch (NoSuchObjectException e) {
//			printer.printFail(e.getMessage());
//		}
	}
}
