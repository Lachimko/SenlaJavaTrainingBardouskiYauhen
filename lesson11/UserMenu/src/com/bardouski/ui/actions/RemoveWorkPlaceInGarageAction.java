package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bardouski.model.IGarage;
import com.bardouski.requests.Request;
import com.bardouski.responses.Response;
import com.bardouski.ui.actions.parents.FacadePermissionInputAction;

public class RemoveWorkPlaceInGarageAction extends FacadePermissionInputAction {

	private static final String NOT_FOUND = "Workplace in this garage is absent\n";
	private static final String MESSAGE_WORKPLACE = "ID of Workplace to remove: ";
	private static final String MESSAGE_GARAGE = "ID of Garage in which remove: ";
	
	public RemoveWorkPlaceInGarageAction(ObjectInputStream in, ObjectOutputStream out, String propertyValue) {
		super(in, out, propertyValue);
	}
	
	@Override
	public void execute() {
		
		if (permission){

			IGarage garage;
			Response response;
			printer.print(MESSAGE_GARAGE);

			try {
				out.writeObject(new Request("getGarage", scanner.nextInt()));

				while ((response = (Response) in.readObject()) != null) {
	
					if ((garage = (IGarage) response.getResponceResult()) != null){
						printer.print(MESSAGE_WORKPLACE);
						out.writeObject(new Request("removeWorkPlaceInGarage", garage, scanner.nextInt()));
					} else {
						printer.printFail(NOT_FOUND);
					}
					
					break;
				}
				
			} catch (IOException | ClassNotFoundException e) {
				logger.error(e.getMessage(), e);
				printer.printFail(e.getMessage());
			}

		} else {
			printer.printFail(NO_PERMISSIONS);
		}
	
	}
}
