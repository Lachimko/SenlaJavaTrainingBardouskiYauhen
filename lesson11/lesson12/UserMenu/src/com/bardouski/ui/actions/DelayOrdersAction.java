package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bardouski.model.IOrder;
import com.bardouski.requests.Request;
import com.bardouski.responses.Response;
import com.bardouski.ui.actions.parents.FacadePermissionInputAction;

public class DelayOrdersAction extends FacadePermissionInputAction {

	private static final String HOW_MANY_DAYS = "For how many days delay other orders:";
	private static final String DELAYED_ID_REQUEST = "Input Id of delayed order";

	public DelayOrdersAction(ObjectInputStream in, ObjectOutputStream out, String propertiesValue) {
		super(in, out, propertiesValue);
	}

	@Override
	public void execute() {

		if (permission) {

			printer.print(DELAYED_ID_REQUEST);
			try {
				IOrder delayed = null;
				Response response;
				
				out.writeObject(new Request("getOrder", scanner.nextInt()));
				
				while ((response = (Response) in.readObject()) != null) {

					delayed = (IOrder) response.getResponceResult();
					break;
				}

				printer.print(HOW_MANY_DAYS);
				out.writeObject(new Request("replaceDatesOfOrdersFrom", delayed, scanner.nextInt()));

			} catch (IOException | ClassNotFoundException e) {
				printer.printFail(e.getMessage());
			}

		} else {
			printer.printFail(NO_PERMISSIONS);
		}

	}
}
