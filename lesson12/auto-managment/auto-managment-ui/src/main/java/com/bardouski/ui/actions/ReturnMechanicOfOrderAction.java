package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bardouski.model.IOrder;
import com.bardouski.requests.Request;
import com.bardouski.responses.Response;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class ReturnMechanicOfOrderAction extends FacadeInputAction {

	private static final String MESSAGE = "Order Id: ";

	public ReturnMechanicOfOrderAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public void execute() {

		printer.print(MESSAGE);

		IOrder order = null;
		Response response;
		try {
			out.writeObject(new Request("getOrder", scanner.nextInt()));
			
			while ((response = (Response) in.readObject()) != null) {
			
				if ((order = (IOrder) response.getResponceResult()) != null){
					printer.print(order.getMechanic());
				} 
				
				break;
			}
			
		} catch (IOException | ClassNotFoundException e) {
			printer.printFail(e.getMessage());
		}
	}
}
