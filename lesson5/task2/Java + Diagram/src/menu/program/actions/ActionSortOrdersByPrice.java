package menu.program.actions;

import bardouski.senla.training.facade.Facade;
import menu.program.actions.parents.FacadeAction;

public class ActionSortOrdersByPrice extends FacadeAction{

	public ActionSortOrdersByPrice(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		printer.print(facade.sortOrderByPrice());
	}
}
