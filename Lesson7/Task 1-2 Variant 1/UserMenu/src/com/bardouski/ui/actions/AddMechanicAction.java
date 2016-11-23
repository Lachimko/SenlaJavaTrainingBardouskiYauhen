package com.bardouski.ui.actions;

import com.bardouski.program.facade.IFacade;
import com.bardouski.program.model.IMechanic;
import com.bardouski.program.model.Mechanic;
import com.bardouski.propertiesholder.PropertiesContext;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class AddMechanicAction extends FacadeInputAction {

	private static final String MESSAGE = "Enter Mechnanic FullName: ";

	public AddMechanicAction(IFacade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		printer.print(MESSAGE);
		String mechanicName = scanner.nextLine();
		try {
			Mechanic mechanic = (Mechanic) PropertiesContext.getInstance(IMechanic.class);
			mechanic.setFullName(mechanicName);
			facade.addMechanic(mechanic);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		}

	}

}
