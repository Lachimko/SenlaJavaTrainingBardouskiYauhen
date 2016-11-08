package menu.program.actions;

import bardouski.senla.training.facade.Facade;
import menu.program.actions.parents.FacadeAction;

public class SaveToDBAction extends FacadeAction{

	public SaveToDBAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		facade.saveToFile();
	}

}
