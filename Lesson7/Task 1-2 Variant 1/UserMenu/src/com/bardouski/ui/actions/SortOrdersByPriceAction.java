package com.bardouski.ui.actions;

import com.bardouski.program.comparators.OrderPriceComparator;
import com.bardouski.program.facade.IFacade;
import com.bardouski.ui.actions.parents.FacadeAction;

public class SortOrdersByPriceAction extends FacadeAction{

	public SortOrdersByPriceAction(IFacade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		printer.print(facade.returnOrdersSortedByComparator(new OrderPriceComparator()));
	}
}
