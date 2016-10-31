package menu.program.actions;

import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Garage;
import menu.program.actions.parents.FacadeInputAction;

public class ActionAddWorkPlaceInGarage extends FacadeInputAction {

	private static final String MESSAGE = "Enter Garage ID where need to add workplace: ";

	public ActionAddWorkPlaceInGarage(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		Garage garage = null;
		printer.print(MESSAGE);

		try {
			garage = facade.getGarage(scanner.nextInt());
			facade.addWorkPlaceInGarage(garage);
		} catch (NoSuchObjectException e) {
			e.getMessage();
		}
	}
}