package menu.program.actions;

import bardouski.senla.training.comparators.OrderPriceComparator;
import bardouski.senla.training.facade.Facade;
import menu.program.actions.parents.FacadeAction;

public class SortOrdersByPriceAction extends FacadeAction{

	public SortOrdersByPriceAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		printer.print(facade.returnOrdersSortedByComparator(new OrderPriceComparator()));
	}
}
