package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bardouski.ui.actions.parents.FacadeInputAction;

public class AddMechanicAction extends FacadeInputAction {

	private static final String MESSAGE = "Enter Mechnanic FullName: ";

	public AddMechanicAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public String execute() {

		printer.print(MESSAGE);
		
		String mechanicName = scanner.nextLine();
		requestContainer.pushMethodNameAndParameters("addMechanic", mechanicName);
		System.out.println(requestContainer);
		//String request = "method:addMechanic,params:" + mechanicName.getClass().getSimpleName() + "_" + mechanicName;
		
		try {
			out.writeObject(requestContainer);
		} catch (IOException e) {
		}
		return null;
		
		// send method: new, methodName:addMechanic, Params:Mechanic, return: Responce: null
		
//		try {
//			Mechanic mechanic = (Mechanic) PropertiesContext.getInstance(IMechanic.class);
//			mechanic.setFullName(mechanicName);
//			facade.addMechanic(mechanic);
//		} catch (ClassNotFoundException e) {
//			logger.error(e.getMessage());
//		}

	}

}
