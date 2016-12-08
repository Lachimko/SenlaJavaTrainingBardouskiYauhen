package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bardouski.model.IMechanic;
import com.bardouski.requests.Request;
import com.bardouski.responses.Response;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class RemoveMechanicAction extends FacadeInputAction {

	private static final String MESSAGE = "Enter Mechnanic's ID to remove: ";
	
	public RemoveMechanicAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}
	
	@Override
	public void execute() {

		printer.print(MESSAGE);

		Response response;
		IMechanic mechanic = null;
		try {
			out.writeObject(new Request("getMechanic", scanner.nextInt()));
			
			while ((response = (Response) in.readObject()) != null) {
				mechanic = (IMechanic) response.getResponceResult();
				out.writeObject(new Request("removeMechanic", mechanic));
				break;
			}
			
		} catch (IOException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
			printer.printFail(e.getMessage());
		}
	}

}
