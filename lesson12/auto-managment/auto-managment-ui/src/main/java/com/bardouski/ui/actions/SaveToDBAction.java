package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bardouski.requests.Request;
import com.bardouski.ui.actions.parents.FacadeAction;

public class SaveToDBAction extends FacadeAction{

	public SaveToDBAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public void execute() {
		try {
			out.writeObject(new Request("saveToFile"));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
