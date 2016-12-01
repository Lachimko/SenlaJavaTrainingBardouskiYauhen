package com.bardouski.ui.actions;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.bardouski.facade.IFacade;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class GetNearestFreeDateAction extends FacadeInputAction {

	public GetNearestFreeDateAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public String execute() {
		return null;

//		Date date = scanner.parseTodayDate();
//		Calendar cal = Calendar.getInstance();
//		List<WorkPlace> list = new ArrayList<>();
//
//		while (true) {
//
//			list = facade.getFreePlacesInDate(date);
//			if (list.isEmpty()) {
//
//				cal.setTime(date);
//				cal.add(Calendar.DAY_OF_MONTH, 1);
//				date = cal.getTime();
//			} else {
//				break;
//			}
//		}
//
//		printer.print(date);
	}

}
