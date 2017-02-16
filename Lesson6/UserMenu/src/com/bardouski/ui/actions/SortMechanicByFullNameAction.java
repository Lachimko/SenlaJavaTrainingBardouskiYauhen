package com.bardouski.ui.actions;

import java.util.List;

import com.bardouski.program.facade.Facade;
import com.bardouski.program.model.Mechanic;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class SortMechanicByFullNameAction extends FacadeInputAction {

	private static final String EMPTY_COLLECTION = "Empty Collection";

	public SortMechanicByFullNameAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		
		List<Mechanic> list = facade.sortMechanicsByFullName();
		
		if(list != null) {
			printer.print(list);
		} else {
			printer.printFail(EMPTY_COLLECTION);
		}
	}
}
