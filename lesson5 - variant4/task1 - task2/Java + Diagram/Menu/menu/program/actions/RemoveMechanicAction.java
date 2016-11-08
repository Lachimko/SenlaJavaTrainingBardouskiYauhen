package menu.program.actions;

import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Mechanic;
import menu.program.actions.parents.FacadeInputAction;

public class RemoveMechanicAction extends FacadeInputAction {

	private static final String MESSAGE = "Enter Mechnanic's ID to remove: ";
	
	public RemoveMechanicAction(Facade facade) {
		super(facade);
	}
	
	@Override
	public void execute() {

		printer.print(MESSAGE);
		
		try {
			Mechanic mechanic = facade.getMechanic(scanner.nextInt());
			facade.removeMechanic(mechanic);
		} catch (NoSuchObjectException e) {
			logger.error(e.getMessage());
			printer.printFail(e.getMessage());
		}
	}

}
