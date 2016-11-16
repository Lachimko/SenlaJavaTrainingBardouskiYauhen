package com.bardouski.ui.actions;

import org.apache.log4j.Logger;

import com.bardouski.config.AutoManagmentPropertiesHolder;
import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.facade.Facade;
import com.bardouski.program.model.Garage;
import com.bardouski.ui.actions.parents.FacadePermissionInputAction;

public class AddWorkPlaceInGarageAction extends FacadePermissionInputAction {

	private static final String MESSAGE = "Enter Garage ID where need to add workplace: ";
	
	public AddWorkPlaceInGarageAction(Facade facade, AutoManagmentPropertiesHolder propertiesHolder) {
		super(facade, propertiesHolder);
	}
	
	@Override
	public void execute() {

		if (propertiesHolder.isAddRemoveWorkplace() == true){
			
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
