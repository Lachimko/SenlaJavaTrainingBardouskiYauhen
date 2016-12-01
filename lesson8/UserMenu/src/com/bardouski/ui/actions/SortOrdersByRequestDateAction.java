package com.bardouski.ui.actions;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bardouski.facade.IFacade;
import com.bardouski.ui.actions.parents.FacadeAction;

public class SortOrdersByRequestDateAction extends FacadeAction{

	public SortOrdersByRequestDateAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public String execute() {
		return null;
		//printer.print(facade.returnOrdersSortedByComparator(new OrderRequestDateComparator()));
	}
}
