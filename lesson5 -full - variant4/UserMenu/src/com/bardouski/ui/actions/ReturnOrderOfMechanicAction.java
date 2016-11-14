package com.bardouski.ui.actions;

import java.util.InputMismatchException;

import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.facade.Facade;
import com.bardouski.program.model.Mechanic;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class ReturnOrderOfMechanicAction extends FacadeInputAction{

	private static final String WRONG_VALUE = "Wrong Value";
	private static final String NO_WORK = "Mechanic haven't any work yet.";
	private static final String MESSAGE = "Mechanic's Id: ";
	
	public ReturnOrderOfMechanicAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		printer.print(MESSAGE);

		try {
			Mechanic mechanic = facade.getMechanic(scanner.nextInt());
			if (mechanic.getCurrentOrder() == null){
				printer.print(NO_WORK);
			} else {
				printer.print(mechanic.getCurrentOrder());
			}
		} catch (NoSuchObjectException e) {
			printer.printFail(e.getMessage());
		}  catch (InputMismatchException e) {
			printer.printFail(WRONG_VALUE);
		}
		
	}

}
