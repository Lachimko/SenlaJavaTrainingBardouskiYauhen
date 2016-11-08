package menu.program.actions;

import bardouski.senla.training.comparators.OrderStartDateComparator;
import bardouski.senla.training.facade.Facade;
import menu.program.actions.parents.FacadeAction;

public class SortOrdersByStartDateAction extends FacadeAction{

	public SortOrdersByStartDateAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		printer.print(facade.returnOrdersSortedByComparator(new OrderStartDateComparator()));
	}
}
