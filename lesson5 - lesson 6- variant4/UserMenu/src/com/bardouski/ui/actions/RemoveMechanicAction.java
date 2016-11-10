package com.bardouski.ui.actions;

import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.facade.Facade;
import com.bardouski.program.model.Mechanic;
import com.bardouski.ui.actions.parents.FacadeInputAction;

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
