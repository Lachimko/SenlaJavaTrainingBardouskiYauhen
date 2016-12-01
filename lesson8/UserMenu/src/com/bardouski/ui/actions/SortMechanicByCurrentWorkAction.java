package com.bardouski.ui.actions;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.bardouski.facade.IFacade;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class SortMechanicByCurrentWorkAction extends FacadeInputAction{

	private static final String EMPTY_COLLECTION = "Empty Collection";
	
	public SortMechanicByCurrentWorkAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public String execute() {
		return null;
		
//		List<Mechanic> list = facade.sortMechanicsByCurrentWork();
//
//		if(list != null) {
//			printer.print(list);
//		} else {
//			printer.printFail(EMPTY_COLLECTION);
//		}
	}
}
