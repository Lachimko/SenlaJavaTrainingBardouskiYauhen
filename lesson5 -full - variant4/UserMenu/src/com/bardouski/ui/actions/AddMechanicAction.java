package com.bardouski.ui.actions;

import com.bardouski.program.facade.Facade;
import com.bardouski.program.model.Mechanic;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class AddMechanicAction extends FacadeInputAction{

	private static final String MESSAGE = "Enter Mechnanic FullName: ";
	
	public AddMechanicAction(Facade facade) {
		super(facade);
	}
	
	@Override
	public void execute() {
		
		printer.print(MESSAGE);
		facade.addMechanic(new Mechanic(scanner.nextLine()));
	}

}
