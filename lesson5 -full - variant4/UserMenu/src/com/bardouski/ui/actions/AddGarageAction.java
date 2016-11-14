package com.bardouski.ui.actions;

import com.bardouski.program.facade.Facade;
import com.bardouski.program.model.Garage;
import com.bardouski.ui.actions.parents.FacadeAction;

public class AddGarageAction extends FacadeAction {

	private static final String GARAGE_ADDED = "Garage added.";

	public AddGarageAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		
		Garage garage = new Garage();
		facade.addGarage(garage);
		printer.print(GARAGE_ADDED);
	}
}
