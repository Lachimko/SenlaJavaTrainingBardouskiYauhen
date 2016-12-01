package com.bardouski.ui.actions;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.NoSuchObjectException;

import com.bardouski.facade.IFacade;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class RemoveMechanicAction extends FacadeInputAction {

	private static final String MESSAGE = "Enter Mechnanic's ID to remove: ";
	
	public RemoveMechanicAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}
	
	@Override
	public String execute() {
		return null;

//		printer.print(MESSAGE);
//
//		try {
//			Mechanic mechanic = facade.getMechanic(scanner.nextInt());
//			facade.removeMechanic(mechanic);
//		} catch (NoSuchObjectException e) {
//			printer.printFail(e.getMessage());
//		}
	}

}
