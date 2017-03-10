package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bardouski.model.IOrder;
import com.bardouski.model.impl.enums.OrderStatus;
import com.bardouski.requests.Request;
import com.bardouski.responses.Response;
import com.bardouski.ui.actions.parents.FacadePermissionInputAction;

public class DeleteOrderAction extends FacadePermissionInputAction {

	private static final String ORDER_ID = "Order Id to delete:";

	public DeleteOrderAction(ObjectInputStream in, ObjectOutputStream out, String propertyValue) {
		super(in, out, propertyValue);
	}

	@Override
	public void execute() {

		if (permission) {

			printer.print(ORDER_ID);
			try {
				IOrder order = null;
				out.writeObject(new Request("getOrder", scanner.nextInt()));

				Response response;
				while ((response = (Response) in.readObject()) != null) {

					order = (IOrder) response.getResponceResult();
					break;
				}
				order.setOrderStatus(OrderStatus.DELETED);
			} catch (IOException | ClassNotFoundException e) {
				logger.error(e.getMessage(), e);
				printer.printFail(e.getMessage());
			}

		} else {
			printer.printFail(NO_PERMISSIONS);
		}
	}
}
