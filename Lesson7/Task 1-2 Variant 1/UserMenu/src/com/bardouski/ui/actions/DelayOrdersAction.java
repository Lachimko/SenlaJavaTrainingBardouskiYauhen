package com.bardouski.ui.actions;

import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.facade.IFacade;
import com.bardouski.program.model.Order;
import com.bardouski.ui.actions.parents.FacadePermissionInputAction;

public class DelayOrdersAction extends FacadePermissionInputAction{

	private static final String HOW_MANY_DAYS = "For how many days delay other orders:";
	private static final String DELAYED_ID_REQUEST = "Input Id of delayed order";

	public DelayOrdersAction(IFacade facade, String propertiesValue) {
		super(facade, propertiesValue);
	}

	@Override
	public void execute() {

		if (permission) {
			
			printer.print(DELAYED_ID_REQUEST);
			try {
				Order delayed = facade.getOrder(scanner.nextInt());
				printer.print(HOW_MANY_DAYS);
				facade.replaceDatesOfOrdersFrom(delayed, scanner.nextInt());
			} catch (NoSuchObjectException e) {
				printer.printFail(e.getMessage());
			}
			
		} else {
			printer.printFail(NO_PERMISSIONS);
		}
		
	}
}
