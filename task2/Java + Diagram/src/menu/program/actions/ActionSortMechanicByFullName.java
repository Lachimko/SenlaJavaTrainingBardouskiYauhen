package menu.program.actions;

import bardouski.senla.training.facade.Facade;
import menu.program.actions.parents.FacadeInputAction;

public class ActionSortMechanicByFullName extends FacadeInputAction {

	public ActionSortMechanicByFullName(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		printer.print(facade.sortMechanicsByFullName());
	}
}
