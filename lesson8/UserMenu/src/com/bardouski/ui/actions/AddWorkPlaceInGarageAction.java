package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bardouski.requests.Request;
import com.bardouski.responses.Response;
import com.bardouski.ui.actions.parents.FacadePermissionInputAction;

public class AddWorkPlaceInGarageAction extends FacadePermissionInputAction {

	private static final String MESSAGE = "Enter Garage ID where need to add workplace: ";
	
	public AddWorkPlaceInGarageAction(ObjectInputStream in, ObjectOutputStream out, String propertyValue) {
		super(in, out, propertyValue);
	}
	
	@Override
	public void execute() {
		
		if (permission == true){

			printer.print(MESSAGE);

			try {
				out.writeObject(new Request("getGarage", scanner.nextInt()));
				Response response;
	
				while ((response = (Response) in.readObject()) != null) {
					out.writeObject(new Request("addWorkPlaceInGarage", response.getResponceResult()));
					break;
				}
				
			} catch (ClassNotFoundException | IOException e) {
				logger.error(e.getMessage(), e);
				printer.printFail(e.getMessage());
			}
		} else {
			printer.printFail(NO_PERMISSIONS);
		}
	}
}
