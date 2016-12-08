package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;

import com.bardouski.model.IMechanic;
import com.bardouski.requests.Request;
import com.bardouski.responses.Response;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class ReturnOrderOfMechanicAction extends FacadeInputAction {

	private static final String WRONG_VALUE = "Wrong Value";
	private static final String NO_WORK = "Mechanic haven't any work yet.";
	private static final String MESSAGE = "Mechanic's Id: ";

	public ReturnOrderOfMechanicAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public void execute() {

		printer.print(MESSAGE);

		IMechanic mechanic = null;
		Response response;
		try {
			out.writeObject(new Request("getMechanic", scanner.nextInt()));

			while ((response = (Response) in.readObject()) != null) {

				if ((mechanic = (IMechanic) response.getResponceResult()) != null) {
					if (mechanic.getCurrentOrder() == null) {
						printer.print(NO_WORK);
					} else {
						printer.print(mechanic.getCurrentOrder());
					}
				} else {
					printer.printFail(WRONG_VALUE);
				}

				break;
			}
		} catch (InputMismatchException e) {
			printer.printFail(WRONG_VALUE);
		} catch (IOException | ClassNotFoundException e) {
			printer.printFail(e.getMessage());
			e.printStackTrace();
		}

	}

}
