package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bardouski.model.IOrder;
import com.bardouski.requests.Request;
import com.bardouski.responses.Response;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class RemoveOrderAction extends FacadeInputAction{

	private static final String ORDER_ID = "Order ID to delete:";

	public RemoveOrderAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public void execute() {
		
		printer.print(ORDER_ID);

		IOrder order = null;
		Response response;

		try {
			out.writeObject(new Request("getOrder", scanner.nextInt()));
			while ((response = (Response) in.readObject()) != null) {
				order = (IOrder) response.getResponceResult();
				out.writeObject(new Request("removeOrder", order));
				break;
			}
			printer.print("Order successfull removed");
		} catch (IOException | ClassNotFoundException e) {
			printer.printFail(e.getMessage());
		}
	}
}
