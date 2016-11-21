package com.bardouski.ui.actions;

import com.bardouski.program.comparators.OrderStartDateComparator;
import com.bardouski.program.facade.Facade;
import com.bardouski.ui.actions.parents.FacadeAction;

public class SortOrdersByStartDateAction extends FacadeAction{

	public SortOrdersByStartDateAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		printer.print(facade.returnOrdersSortedByComparator(new OrderStartDateComparator()));
	}
}
