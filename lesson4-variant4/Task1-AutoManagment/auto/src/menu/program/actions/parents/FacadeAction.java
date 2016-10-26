package menu.program.actions.parents;

import bardouski.senla.training.facade.Facade;

public abstract class FacadeAction implements IAction {

	protected Facade facade;

	public FacadeAction(Facade facade) {
		this.facade = facade;
	}

}
