package menu.program.actions;

import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Mechanic;
import menu.program.actions.parents.FacadeInputAction;

public class ActionReturnOrderOfMechanic extends FacadeInputAction{

	private static final String NO_WORK = "Mechanic haven't any work yet.";
	private static final String MESSAGE = "Mechanic's Id: ";
	
	public ActionReturnOrderOfMechanic(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		printer.print(MESSAGE);

		try {
			Mechanic mechanic = facade.getMechanic(scanner.nextInt());
			if (mechanic.getCurrentOrder() == null){
				printer.print(NO_WORK);
			} else {
				printer.print(mechanic.getCurrentOrder());
			}
		} catch (NoSuchObjectException e) {
			printer.printFail(e.getMessage());
		}
	}

}
