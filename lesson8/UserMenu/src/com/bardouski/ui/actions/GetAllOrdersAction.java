package com.bardouski.ui.actions;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bardouski.facade.IFacade;
import com.bardouski.ui.actions.parents.FacadeAction;

public class GetAllOrdersAction extends FacadeAction{

	public GetAllOrdersAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public String execute() {
		return null;
		//printer.print(facade.getAllOrders());
	}
}
