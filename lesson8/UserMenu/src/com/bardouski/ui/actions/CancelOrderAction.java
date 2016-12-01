package com.bardouski.ui.actions;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.NoSuchObjectException;

import org.apache.log4j.Logger;

import com.bardouski.facade.IFacade;
import com.bardouski.model.enums.OrderStatus;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class CancelOrderAction extends FacadeInputAction{

	private static final String ORDER_ID = "Order Id to cancel:";

	public CancelOrderAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public String execute() {
		return null;

//		printer.print(ORDER_ID);
//		try {
//			Order order = facade.getOrder(scanner.nextInt());
//			order.setOrderStatus(OrderStatus.CANCELLED);
//		} catch (NoSuchObjectException e) {
//			logger = Logger.getLogger(this.getClass().getSimpleName());
//			logger.error(e.getMessage());
//			printer.printFail(e.getMessage());
//		}
	}
}
