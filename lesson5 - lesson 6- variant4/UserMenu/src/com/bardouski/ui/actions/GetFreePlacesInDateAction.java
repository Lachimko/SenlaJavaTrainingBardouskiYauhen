package com.bardouski.ui.actions;

import java.text.ParseException;
import java.util.List;

import com.bardouski.program.facade.Facade;
import com.bardouski.program.model.WorkPlace;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class GetFreePlacesInDateAction extends FacadeInputAction {

	private static final String PARSE_ERROR = "Parse Error";
	private static final String INPUT_DATE = "Input Date:";

	public GetFreePlacesInDateAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		printer.print(INPUT_DATE);
		try {
			int mechanicsStateCount = facade.getAllMechanics().size();
			List<WorkPlace> list = facade.getFreePlacesInDate(scanner.parseDate(scanner.nextLine()));
			
			// if mechanics count are less than all places return mechanics count
			if (mechanicsStateCount > list.size()){
				printer.print(list.size());
			} else {
				printer.print(mechanicsStateCount);
			}
		} catch (ParseException e) {
			logger.error(PARSE_ERROR);
			printer.printFail(PARSE_ERROR);
		}

	}

}
