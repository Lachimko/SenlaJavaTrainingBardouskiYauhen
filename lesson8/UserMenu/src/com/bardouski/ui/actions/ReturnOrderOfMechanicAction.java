package com.bardouski.ui.actions;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.NoSuchObjectException;
import java.util.InputMismatchException;

import com.bardouski.facade.IFacade;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class ReturnOrderOfMechanicAction extends FacadeInputAction{

	private static final String WRONG_VALUE = "Wrong Value";
	private static final String NO_WORK = "Mechanic haven't any work yet.";
	private static final String MESSAGE = "Mechanic's Id: ";
	
	public ReturnOrderOfMechanicAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public String execute() {

		printer.print(MESSAGE);
		return null;

//		try {
//			Mechanic mechanic = facade.getMechanic(scanner.nextInt());
//			if (mechanic.getCurrentOrder() == null){
//				printer.print(NO_WORK);
//			} else {
//				printer.print(mechanic.getCurrentOrder());
//			}
//		} catch (NoSuchObjectException e) {
//			printer.printFail(e.getMessage());
//		}  catch (InputMismatchException e) {
//			printer.printFail(WRONG_VALUE);
//		}
		
	}

}
