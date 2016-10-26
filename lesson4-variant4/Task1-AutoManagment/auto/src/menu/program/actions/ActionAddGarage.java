package menu.program.actions;

import bardouski.senla.training.facade.Facade;
import menu.program.actions.parents.FacadeAction;

public class ActionAddGarage extends FacadeAction {

	public ActionAddGarage(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		facade.addGarage();
	}
}
