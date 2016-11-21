package com.bardouski.ui.actions;

import com.bardouski.program.comparators.OrderRequestDateComparator;
import com.bardouski.program.facade.Facade;
import com.bardouski.ui.actions.parents.FacadeAction;

public class SortOrdersByRequestDateAction extends FacadeAction{

	public SortOrdersByRequestDateAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		printer.print(facade.returnOrdersSortedByComparator(new OrderRequestDateComparator()));
	}
}
