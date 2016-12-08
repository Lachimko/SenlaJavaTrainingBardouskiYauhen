package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bardouski.requests.Request;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class AddMechanicAction extends FacadeInputAction {

	private static final String MESSAGE = "Enter Mechnanic FullName: ";

	public AddMechanicAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public void execute() {

		printer.print(MESSAGE);

		try {
			out.writeObject(new Request("addMechanic", scanner.nextLine()));
			
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
