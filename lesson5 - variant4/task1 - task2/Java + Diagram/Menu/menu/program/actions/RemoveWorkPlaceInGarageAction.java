package menu.program.actions;

import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Garage;
import menu.program.actions.parents.FacadeInputAction;

public class RemoveWorkPlaceInGarageAction extends FacadeInputAction {

	private static final String NOT_FOUND = "Workplace in this garage is absent\n";
	private static final String MESSAGE_WORKPLACE = "ID of Workplace to remove: ";
	private static final String MESSAGE_GARAGE = "ID of Garage in which remove: ";

	public RemoveWorkPlaceInGarageAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		printer.print(MESSAGE_GARAGE);
		int garID = scanner.nextInt();

		try {
			Garage garage = facade.getGarage(garID);

			printer.print(MESSAGE_WORKPLACE);
			int wpID = scanner.nextInt();

			if (!facade.removeWorkPlaceInGarage(garage, wpID)) {
				printer.print(NOT_FOUND);
			}

		} catch (NoSuchObjectException e) {
			logger.error(e.getMessage());
			printer.printFail(e.getMessage());
		}
	}
}
