package menu.program.actions;

import bardouski.senla.training.facade.Facade;
import menu.program.actions.parents.FacadeAction;

public class ActionSortMechanicByCurrentWork extends FacadeAction{

	public ActionSortMechanicByCurrentWork(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		facade.sortMechanicsByCurrentWork();
	}
}
