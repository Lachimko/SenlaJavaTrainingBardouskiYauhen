package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.NoSuchObjectException;

import org.apache.log4j.Logger;

import com.bardouski.model.IOrder;
import com.bardouski.model.impl.enums.OrderStatus;
import com.bardouski.requests.Request;
import com.bardouski.responses.Response;
import com.bardouski.ui.actions.parents.FacadeInputAction;

/**
 * Action generate the clone of chosen instance of Order. Change id and add to
 * OrderStore collection
 */
public class CloneOrderAction extends FacadeInputAction {

	private static final String INPUT_ORDER_ID = "Order Id to clone:";

	public CloneOrderAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public void execute() {

		try {
			IOrder order;
			out.writeObject(new Request("getOrder", scanner.nextInt()));

			Response response;

			while ((response = (Response) in.readObject()) != null) {
				order = (IOrder) response.getResponceResult();
				order.setOrderStatus(OrderStatus.CANCELLED);
				break;
			}

		} catch (IOException | ClassNotFoundException e) {
			logger = Logger.getLogger(this.getClass().getSimpleName());
			logger.error(e.getMessage());
			printer.printFail(e.getMessage());
		}

		printer.print(INPUT_ORDER_ID);
		try {
			out.writeObject(new Request("getOrder", scanner.nextInt()));
			Response response;
			IOrder order = null;
			
			while ((response = (Response) in.readObject()) != null) {
				order = (IOrder) response.getResponceResult();
				break;
			}

			IOrder clonedOrder = (IOrder) order.clone();
			Integer nextOrderId = null;
			out.writeObject(new Request("getNextOrderId"));

			while ((response = (Response) in.readObject()) != null) {
				nextOrderId = (int) response.getResponceResult();
				break;
			}
			clonedOrder.setId(nextOrderId);
			out.writeObject(new Request("addOrder", clonedOrder));

		} catch (NoSuchObjectException e) {
			printer.printFail(e.getMessage());
		} catch (IOException | CloneNotSupportedException | ClassNotFoundException e) {
			logger = Logger.getLogger(this.getClass().getSimpleName());
			printer.printFail(e.getMessage());
			logger.error(e.getMessage());
		} 
	}

}
