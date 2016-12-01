package com.bardouski.ui.actions;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//import java.rmi.NoSuchObjectException;

import com.bardouski.facade.IFacade;
//import com.bardouski.model.enums.OrderStatus;
import com.bardouski.ui.actions.parents.FacadePermissionInputAction;

public class DeleteOrderAction extends FacadePermissionInputAction {

	private static final String ORDER_ID = "Order Id to delete:";

	public DeleteOrderAction(ObjectInputStream in, ObjectOutputStream out, String propertyValue) {
		super(in, out, propertyValue);
	}
	
	@Override
	public String execute() {
		
		if (permission){
			
			printer.print(ORDER_ID);
//			try {
//				Order order = facade.getOrder(scanner.nextInt());
//				order.setOrderStatus(OrderStatus.DELETED);
//			} catch (NoSuchObjectException e) {
//				printer.printFail(e.getMessage());
//			}
			
		} else {
			printer.printFail(NO_PERMISSIONS);
		}
		return null;
		
	}
}
