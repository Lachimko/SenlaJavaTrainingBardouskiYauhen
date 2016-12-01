package com.bardouski.ui.actions;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.NoSuchObjectException;

import com.bardouski.facade.IFacade;
import com.bardouski.ui.actions.parents.FacadePermissionInputAction;

public class RemoveWorkPlaceInGarageAction extends FacadePermissionInputAction {

	private static final String NOT_FOUND = "Workplace in this garage is absent\n";
	private static final String MESSAGE_WORKPLACE = "ID of Workplace to remove: ";
	private static final String MESSAGE_GARAGE = "ID of Garage in which remove: ";
	
	public RemoveWorkPlaceInGarageAction(ObjectInputStream in, ObjectOutputStream out, String propertyValue) {
		super(in, out, propertyValue);
	}
	
	@Override
	public String execute() {
		return null;
		
//		if (permission){
//
//			printer.print(MESSAGE_GARAGE);
//			int garID = scanner.nextInt();
//
//			try {
//				Garage garage = facade.getGarage(garID);
//
//				printer.print(MESSAGE_WORKPLACE);
//				int wpID = scanner.nextInt();
//
//				if (!facade.removeWorkPlaceInGarage(garage, wpID)) {
//					printer.print(NOT_FOUND);
//				}
//
//			} catch (NoSuchObjectException e) {
//				printer.printFail(e.getMessage());
//			}
//
//		} else {
//			printer.printFail(NO_PERMISSIONS);
//		}
	
	}
}
