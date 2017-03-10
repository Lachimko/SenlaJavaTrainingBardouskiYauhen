package com.bardouski.ui.actions;

import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.facade.IFacade;
import com.bardouski.program.model.Order;
import com.bardouski.program.model.enums.OrderStatus;
import com.bardouski.ui.actions.parents.FacadePermissionInputAction;

public class DeleteOrderAction extends FacadePermissionInputAction {

	private static final String ORDER_ID = "Order Id to delete:";

	public DeleteOrderAction(IFacade facade, String propertyValue) {
		super(facade, propertyValue);
	}
	
	@Override
	public void execute() {
		
		if (permission){
			
			printer.print(ORDER_ID);
			try {
				Order order = facade.getOrder(scanner.nextInt());
				order.setOrderStatus(OrderStatus.DELETED);
			} catch (NoSuchObjectException e) {
				printer.printFail(e.getMessage());
			}	
			
		} else {
			printer.printFail(NO_PERMISSIONS);
		}
		
	}
}
