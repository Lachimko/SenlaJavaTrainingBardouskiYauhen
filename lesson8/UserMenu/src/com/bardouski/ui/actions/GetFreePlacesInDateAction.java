package com.bardouski.ui.actions;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.List;

import com.bardouski.facade.IFacade;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class GetFreePlacesInDateAction extends FacadeInputAction {

	private static final String PARSE_ERROR = "Parse Error";
	private static final String INPUT_DATE = "Input Date:";

	public GetFreePlacesInDateAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public String execute() {
		return null;

//		printer.print(INPUT_DATE);
//		try {
//			int mechanicsStateCount = facade.getAllMechanics().size();
//			List<WorkPlace> list = facade.getFreePlacesInDate(scanner.parseDate(scanner.nextLine()));
//
//			// if mechanics count are less than all places return mechanics count
//			if (mechanicsStateCount > list.size()){
//				printer.print(list.size());
//			} else {
//				printer.print(mechanicsStateCount);
//			}
//		} catch (ParseException e) {
//			printer.printFail(PARSE_ERROR);
//		}

	}

}
