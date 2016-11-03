package menu.program.actions;

import java.text.ParseException;
import java.util.List;

import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.WorkPlace;
import menu.program.actions.parents.FacadeInputAction;

public class ActionGetFreePlacesInDate extends FacadeInputAction {

	private static final String PARSE_ERROR = "Parse Error";
	private static final String INPUT_DATE = "Input Date:";

	public ActionGetFreePlacesInDate(Facade facade) {
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
			printer.printFail(PARSE_ERROR);
		}

	}

}
