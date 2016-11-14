package com.bardouski.ui.actions;

import com.bardouski.program.comparators.OrderPriceComparator;
import com.bardouski.program.facade.Facade;
import com.bardouski.ui.actions.parents.FacadeAction;

public class SortOrdersByPriceAction extends FacadeAction{

	public SortOrdersByPriceAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		printer.print(facade.returnOrdersSortedByComparator(new OrderPriceComparator()));
	}
}
