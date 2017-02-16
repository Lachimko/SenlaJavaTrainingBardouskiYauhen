package com.bardouski.ui.actions;

import com.bardouski.program.facade.IFacade;
import com.bardouski.program.model.Garage;
import com.bardouski.program.model.IGarage;
import com.bardouski.propertiesholder.PropertiesContext;
import com.bardouski.ui.actions.parents.FacadeAction;

public class AddGarageAction extends FacadeAction {

	private static final String GARAGE_ADDED = "Garage added.";

	public AddGarageAction(IFacade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		
		Garage garage = null;
		try {
			garage = (Garage) PropertiesContext.getInstance(IGarage.class);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		}
		if (garage != null){
			facade.addGarage(garage);
			printer.print(GARAGE_ADDED);
		}
	}
}
