package com.bardouski.ui.actions;

import com.bardouski.program.facade.Facade;
import com.bardouski.ui.actions.parents.FacadeAction;

public class GetAllOrdersAction extends FacadeAction{

	public GetAllOrdersAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		printer.print(facade.getAllOrders());
	}
}
