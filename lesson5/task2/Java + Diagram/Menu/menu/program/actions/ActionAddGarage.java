package menu.program.actions;

import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Garage;
import menu.program.actions.parents.FacadeAction;

public class ActionAddGarage extends FacadeAction {

	private static final String GARAGE_ADDED = "Garage added.";

	public ActionAddGarage(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		
		Garage garage = new Garage();
		facade.addGarage(garage);
		printer.print(GARAGE_ADDED);
	}
}
