package com.bardouski.ui.actions;

import com.bardouski.program.comparators.OrderStartDateComparator;
import com.bardouski.program.facade.IFacade;
import com.bardouski.ui.actions.parents.FacadeAction;

public class SortOrdersByStartDateAction extends FacadeAction{

	public SortOrdersByStartDateAction(IFacade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		printer.print(facade.returnOrdersSortedByComparator(new OrderStartDateComparator()));
	}
}
