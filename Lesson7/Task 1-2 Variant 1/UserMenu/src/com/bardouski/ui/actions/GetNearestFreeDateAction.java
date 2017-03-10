package com.bardouski.ui.actions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bardouski.program.facade.IFacade;
import com.bardouski.program.model.WorkPlace;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class GetNearestFreeDateAction extends FacadeInputAction {

	public GetNearestFreeDateAction(IFacade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		Date date = scanner.parseTodayDate();
		Calendar cal = Calendar.getInstance();
		List<WorkPlace> list = new ArrayList<>();

		while (true) {

			list = facade.getFreePlacesInDate(date);
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
