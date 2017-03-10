package com.bardouski.ui.actions;

import java.util.List;

import com.bardouski.program.facade.IFacade;
import com.bardouski.program.model.Mechanic;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class SortMechanicByCurrentWorkAction extends FacadeInputAction{

	private static final String EMPTY_COLLECTION = "Empty Collection";
	
	public SortMechanicByCurrentWorkAction(IFacade facade) {
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
