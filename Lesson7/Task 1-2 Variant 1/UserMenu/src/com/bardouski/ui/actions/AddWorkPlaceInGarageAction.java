package com.bardouski.ui.actions;

import org.apache.log4j.Logger;

import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.facade.IFacade;
import com.bardouski.program.model.Garage;
import com.bardouski.ui.actions.parents.FacadePermissionInputAction;

public class AddWorkPlaceInGarageAction extends FacadePermissionInputAction {

	private static final String MESSAGE = "Enter Garage ID where need to add workplace: ";
	
	public AddWorkPlaceInGarageAction(IFacade facade, String propertyValue) {
		super(facade, propertyValue);
	}
	
	@Override
	public void execute() {

		if (permission){
			
			printer.print(MESSAGE);

			try {
				Garage garage = facade.getGarage(scanner.nextInt());
				facade.addWorkPlaceInGarage(garage);
			} catch (NoSuchObjectException e) {
				logger = Logger.getLogger(this.getClass().getSimpleName());
				logger.error(e.getMessage());
				printer.printFail(e.getMessage());
			}
			
		} else {
			printer.printFail(NO_PERMISSIONS);
		}
	}
}
