package com.bardouski.ui.actions;

import com.bardouski.program.comparators.OrderCompleteDateComparator;
import com.bardouski.program.facade.Facade;
import com.bardouski.ui.actions.parents.FacadeAction;

public class SortOrdersByCompleteDateAction extends FacadeAction{

	public SortOrdersByCompleteDateAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		printer.print(facade.returnOrdersSortedByComparator(new OrderCompleteDateComparator()));
	}

}
