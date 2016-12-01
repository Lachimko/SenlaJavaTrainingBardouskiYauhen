package com.bardouski.ui.actions;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bardouski.ui.actions.parents.FacadePermissionInputAction;

public class AddWorkPlaceInGarageAction extends FacadePermissionInputAction {

//	private static final String MESSAGE = "Enter Garage ID where need to add workplace: ";
	
	public AddWorkPlaceInGarageAction(ObjectInputStream in, ObjectOutputStream out, String propertyValue) {
		super(in, out, propertyValue);
	}
	
	@Override
	public String execute() {
		
		return null;

		// send method: new, methodName:addWorkPlaceInGarage, Params:Garage, return: Responce: null
		
//		if (permission){
//
//			printer.print(MESSAGE);
//
//			try {
//				Garage garage = facade.getGarage(scanner.nextInt());
//				facade.addWorkPlaceInGarage(garage);
//			} catch (NoSuchObjectException e) {
//				logger = Logger.getLogger(this.getClass().getSimpleName());
//				logger.error(e.getMessage());
//				printer.printFail(e.getMessage());
//			}
//
//		} else {
//			printer.printFail(NO_PERMISSIONS);
//		}
	}
}
