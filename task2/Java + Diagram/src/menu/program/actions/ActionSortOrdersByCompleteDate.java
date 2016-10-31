package menu.program.actions;

import bardouski.senla.training.facade.Facade;
import menu.program.actions.parents.FacadeAction;

public class ActionSortOrdersByCompleteDate extends FacadeAction{

	public ActionSortOrdersByCompleteDate(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		printer.print(facade.sortOrderByCompleteDate());
	}

}
