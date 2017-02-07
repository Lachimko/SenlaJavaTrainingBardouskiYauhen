package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.List;

import com.bardouski.model.IMechanic;
import com.bardouski.model.IWorkPlace;
import com.bardouski.requests.Request;
import com.bardouski.responses.Response;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class GetFreePlacesInDateAction extends FacadeInputAction {

	private static final String PARSE_ERROR = "Parse Error";
	private static final String INPUT_DATE = "Input Date:";

	public GetFreePlacesInDateAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public void execute() {

		printer.print(INPUT_DATE);
		try {
			Response response;
			Integer mechanicsStateCount = null;
			Integer workPlacesInDateCount = null;
			out.writeObject(new Request("getAllMechanics"));

			while ((response = (Response) in.readObject()) != null) {
				@SuppressWarnings("unchecked")
				List<IMechanic> mechanics = (List<IMechanic>) response.getResponceResult();
				mechanicsStateCount = mechanics.size();
				break;
			}

			out.writeObject(new Request("getFreePlacesInDate", scanner.parseDate(scanner.nextLine())));

			while ((response = (Response) in.readObject()) != null) {
				@SuppressWarnings("unchecked")
				List<IWorkPlace> workPlaces = (List<IWorkPlace>) response.getResponceResult();
				workPlacesInDateCount = workPlaces.size();
				break;
			}
			
			if (mechanicsStateCount > workPlacesInDateCount) {
				printer.print(workPlacesInDateCount);
			} else {
				printer.print(mechanicsStateCount);
			}
		} catch (ParseException e) {
			printer.printFail(PARSE_ERROR);
		} catch (ClassNotFoundException | IOException e) {
			logger.error(e.getMessage(), e);
		}

	}

}
