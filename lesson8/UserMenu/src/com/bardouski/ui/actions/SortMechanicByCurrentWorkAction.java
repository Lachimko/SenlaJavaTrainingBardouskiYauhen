package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.bardouski.model.IMechanic;
import com.bardouski.requests.Request;
import com.bardouski.responses.Response;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class SortMechanicByCurrentWorkAction extends FacadeInputAction {

	private static final String EMPTY_COLLECTION = "Empty Collection";

	public SortMechanicByCurrentWorkAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {

		List<IMechanic> list = null;
		Response response;

		try {
			out.writeObject(new Request("sortMechanicsByCurrentWork"));

			while ((response = (Response) in.readObject()) != null) {

				list = (List<IMechanic>) response.getResponceResult();
				if (list != null) {
					printer.print(list);
				} else {
					printer.printFail(EMPTY_COLLECTION);
				}
				break;
			}

		} catch (IOException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
}
