package com.bardouski.ui.actions;

import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.facade.IFacade;
import com.bardouski.program.model.Garage;
import com.bardouski.ui.actions.parents.FacadePermissionInputAction;

public class RemoveWorkPlaceInGarageAction extends FacadePermissionInputAction {

	private static final String NOT_FOUND = "Workplace in this garage is absent\n";
	private static final String MESSAGE_WORKPLACE = "ID of Workplace to remove: ";
	private static final String MESSAGE_GARAGE = "ID of Garage in which remove: ";
	
	public RemoveWorkPlaceInGarageAction(IFacade facade, String propertyValue) {
		super(facade, propertyValue);
	}
	
	@Override
	public void execute() {
		
		if (permission){
			
			printer.print(MESSAGE_GARAGE);
			int garID = scanner.nextInt();

			try {
				Garage garage = facade.getGarage(garID);

				printer.print(MESSAGE_WORKPLACE);
				int wpID = scanner.nextInt();

				if (!facade.removeWorkPlaceInGarage(garage, wpID)) {
					printer.print(NOT_FOUND);
				}

			} catch (NoSuchObjectException e) {
				printer.printFail(e.getMessage());
			}
			
		} else {
			printer.printFail(NO_PERMISSIONS);
		}
	
	}
}
