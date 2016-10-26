package menu.program.actions;

import java.util.List;

import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Mechanic;
import menu.program.actions.parents.FacadeAction;

public class ActionSortMechanicByFullName extends FacadeAction{

	public ActionSortMechanicByFullName(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		
		List<Mechanic> list = facade.sortMechanicsByFullName();
		for (Mechanic mechanic : list) {
			System.out.println(mechanic);
		}
	}

}
