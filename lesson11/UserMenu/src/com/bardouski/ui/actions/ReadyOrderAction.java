package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bardouski.model.IOrder;
import com.bardouski.model.impl.enums.OrderStatus;
import com.bardouski.requests.Request;
import com.bardouski.responses.Response;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class ReadyOrderAction extends FacadeInputAction{

	private static final String ORDER_ID = "Order id to make ready:";

	public ReadyOrderAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public void execute() {
		
		printer.print(ORDER_ID);
		try {
			IOrder order = null;
			Response response;

			out.writeObject(new Request("getOrder", scanner.nextInt()));
			
			while ((response = (Response) in.readObject()) != null) {
				order = (IOrder) response.getResponceResult();
				break;
			}
			order.setOrderStatus(OrderStatus.READY);
		} catch (IOException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
			printer.printFail(e.getMessage());
		}
	}

}
