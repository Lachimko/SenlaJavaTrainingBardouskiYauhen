package menu.program.actions;

import bardouski.senla.training.exceptions.EmptyCollectionException;
import bardouski.senla.training.facade.Facade;
import menu.program.actions.parents.FacadeInputAction;

public class ActionRemoveWorkPlaceInGarage extends FacadeInputAction{

	private static final String NOT_FOUND = "Garage or Workplace in this garage is absent\n";
	private static final String MESSAGE_WORKPLACE = "ID of Workplace to remove: ";
	private static final String MESSAGE_GARAGE = "ID of Garage in which remove: ";
	
	public ActionRemoveWorkPlaceInGarage(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		printer.print(MESSAGE_WORKPLACE);
		int wpID = scanner.nextInt();
		
		printer.print(MESSAGE_GARAGE);
		int garID = scanner.nextInt();
		
		try {
			if (!facade.removeWorkPlaceInGarage(wpID, garID)){
				printer.printFail(NOT_FOUND);
			};
		} catch (EmptyCollectionException e) {
			e.getMessage();
		}
	}

}
