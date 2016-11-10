package com.bardouski.ui.actions;

import com.bardouski.config.AutoManagmentPropertiesHolder;
import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.facade.Facade;
import com.bardouski.program.model.Order;
import com.bardouski.program.model.enums.OrderStatus;
import com.bardouski.ui.actions.parents.FacadePermissionInputAction;

public class DeleteOrderAction extends FacadePermissionInputAction {

	private static final String ORDER_ID = "Order Id to delete:";

	public DeleteOrderAction(Facade facade, AutoManagmentPropertiesHolder propertiesHolder) {
		super(facade, propertiesHolder);
	}
	
	@Override
	public void execute() {
		
		if (propertiesHolder.isRemoveOrder() == true){
			
			printer.print(ORDER_ID);
			try {
				Order order = facade.getOrder(scanner.nextInt());
				order.setOrderStatus(OrderStatus.DELETED);
			} catch (NoSuchObjectException e) {
				logger.error(e.getMessage());
				printer.printFail(e.getMessage());
			}	
			
		} else {
			printer.printFail(NO_PERMISSIONS);
		}
		
	}
}
