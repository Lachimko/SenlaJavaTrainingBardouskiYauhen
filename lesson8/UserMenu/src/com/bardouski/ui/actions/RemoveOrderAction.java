package com.bardouski.ui.actions;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.NoSuchObjectException;

import com.bardouski.facade.IFacade;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class RemoveOrderAction extends FacadeInputAction{

	private static final String ORDER_ID = "Order ID to delete:";

	public RemoveOrderAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public String execute() {
		return null;
		
//		printer.print(ORDER_ID);
//		try {
//			Order order = facade.getOrder(scanner.nextInt());
//			facade.removeOrder(order);
//			printer.print("Order successfull removed");
//		} catch (NoSuchObjectException e) {
//			printer.printFail(e.getMessage());
//		}
	}
}
