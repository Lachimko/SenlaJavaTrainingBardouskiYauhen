package com.bardouski.ui.actions;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.NoSuchObjectException;

import com.bardouski.facade.IFacade;
import com.bardouski.model.enums.OrderStatus;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class ReadyOrderAction extends FacadeInputAction{

	private static final String ORDER_ID = "Order id to make ready:";

	public ReadyOrderAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public String execute() {
		return null;
		
//		printer.print(ORDER_ID);
//		try {
//			Order order = facade.getOrder(scanner.nextInt());
//			order.setOrderStatus(OrderStatus.READY);
//		} catch (NoSuchObjectException e) {
//			printer.printFail(e.getMessage());
//		}
	}

}
