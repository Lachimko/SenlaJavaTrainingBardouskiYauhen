package menu.program.actions;

import bardouski.senla.training.facade.Facade;
import menu.program.actions.parents.FacadeAction;

public class NoAccessAction extends FacadeAction{

	private static final String NO_ACCESS = "No Access";

	public NoAccessAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		printer.printFail(NO_ACCESS);
	}

}
