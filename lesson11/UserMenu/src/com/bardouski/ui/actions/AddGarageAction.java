package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bardouski.requests.Request;
import com.bardouski.ui.actions.parents.FacadeAction;

public class AddGarageAction extends FacadeAction {

	public AddGarageAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public void execute() throws IOException {
		out.writeObject(new Request("addGarage"));
	}
}
