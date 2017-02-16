package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.bardouski.model.IOrder;
import com.bardouski.model.impl.enums.OrderStatus;
import com.bardouski.requests.Request;
import com.bardouski.responses.Response;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class CancelOrderAction extends FacadeInputAction {

	private static final String ORDER_ID = "Order Id to cancel:";

	public CancelOrderAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public void execute() {

		printer.print(ORDER_ID);

		try {
			out.writeObject(new Request("getOrder", scanner.nextInt()));
			Response response;
			
			while ((response = (Response) in.readObject()) != null) {
				IOrder order = (IOrder) response.getResponceResult();
				order.setOrderStatus(OrderStatus.CANCELLED);
				break;
			}
			
		} catch (IOException | ClassNotFoundException e) {
			logger = Logger.getLogger(this.getClass().getSimpleName());
			logger.error(e.getMessage());
			printer.printFail(e.getMessage());
		}
	}
}
