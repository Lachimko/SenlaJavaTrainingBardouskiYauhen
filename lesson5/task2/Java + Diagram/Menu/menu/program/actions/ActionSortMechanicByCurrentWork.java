package menu.program.actions;

import java.util.List;

import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Mechanic;
import menu.program.actions.parents.FacadeInputAction;

public class ActionSortMechanicByCurrentWork extends FacadeInputAction{

	private static final String EMPTY_COLLECTION = "Empty Collection";
	
	public ActionSortMechanicByCurrentWork(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		
		List<Mechanic> list = facade.sortMechanicsByCurrentWork();
		
		if(list != null) {
			printer.print(list);
		} else {
			printer.printFail(EMPTY_COLLECTION);
		}
	}
}
