package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bardouski.requests.Request;
import com.bardouski.responses.Response;
import com.bardouski.ui.actions.parents.FacadeAction;

public class GetAllOrdersAction extends FacadeAction {

	public GetAllOrdersAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public void execute() {

		try {
			out.writeObject(new Request("getAllOrders"));

			Response response;
			while ((response = (Response) in.readObject()) != null) {

				printer.print(response.getResponceResult());
				break;
			}

		} catch (IOException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
}
