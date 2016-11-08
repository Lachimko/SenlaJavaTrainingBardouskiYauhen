package menu.program.actions;

import bardouski.senla.training.comparators.OrderRequestDateComparator;
import bardouski.senla.training.facade.Facade;
import menu.program.actions.parents.FacadeAction;

public class SortOrdersByRequestDateAction extends FacadeAction{

	public SortOrdersByRequestDateAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		printer.print(facade.returnOrdersSortedByComparator(new OrderRequestDateComparator()));
	}
}
