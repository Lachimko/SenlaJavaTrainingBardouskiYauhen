package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bardouski.model.IGarage;
import com.bardouski.propertiesholder.PropertiesContext;
import com.bardouski.ui.actions.parents.FacadeAction;

public class AddGarageAction extends FacadeAction {

	// private static final String GARAGE_ADDED = "Garage added.";

	public AddGarageAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public String execute() throws IOException {

		try {
			Object garage = PropertiesContext.newInstance(IGarage.class);
			requestContainer.pushMethodNameAndParameters("addGarage", garage);
			out.writeObject(requestContainer);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;

		// send method: new, methodName:addGarage, Params:Garage, return:
		// Responce: null

		// Garage garage = null;
		// try {
		// garage = (Garage) PropertiesContext.getInstance(IGarage.class);
		// } catch (ClassNotFoundException e) {
		// logger.error(e.getMessage());
		// }
		// if (garage != null){
		// facade.addGarage(garage);
		// printer.print(GARAGE_ADDED);
		// }
	}
}
