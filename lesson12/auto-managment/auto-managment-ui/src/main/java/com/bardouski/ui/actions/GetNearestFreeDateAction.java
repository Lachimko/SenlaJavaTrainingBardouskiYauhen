package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bardouski.model.IWorkPlace;
import com.bardouski.requests.Request;
import com.bardouski.responses.Response;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class GetNearestFreeDateAction extends FacadeInputAction {

	public GetNearestFreeDateAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {

		Date date = scanner.parseTodayDate();
		Calendar cal = Calendar.getInstance();

		List<IWorkPlace> list = null;

		while (true) {

			try {
				out.writeObject(new Request("getFreePlacesInDate", date));

				Response response;
				while ((response = (Response) in.readObject()) != null) {
					list = (List<IWorkPlace>) response.getResponceResult();
					break;
				}

			} catch (IOException | ClassNotFoundException e) {
				logger.error(e.getMessage(), e);
			}

			if (list.isEmpty()) {

				cal.setTime(date);
				cal.add(Calendar.DAY_OF_MONTH, 1);
				date = cal.getTime();
			} else {
				break;
			}

		}

		printer.print(date);
	}

}
