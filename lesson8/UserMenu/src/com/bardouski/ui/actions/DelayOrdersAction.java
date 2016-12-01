package com.bardouski.ui.actions;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.NoSuchObjectException;

import com.bardouski.facade.IFacade;
import com.bardouski.ui.actions.parents.FacadePermissionInputAction;

public class DelayOrdersAction extends FacadePermissionInputAction{

	private static final String HOW_MANY_DAYS = "For how many days delay other orders:";
	private static final String DELAYED_ID_REQUEST = "Input Id of delayed order";

	public DelayOrdersAction(ObjectInputStream in, ObjectOutputStream out, String propertiesValue) {
		super(in, out, propertiesValue);
	}

	@Override
	public String execute() {
		return null;

//		if (permission) {
//
//			printer.print(DELAYED_ID_REQUEST);
//			try {
//				Order delayed = facade.getOrder(scanner.nextInt());
//				printer.print(HOW_MANY_DAYS);
//				facade.replaceDatesOfOrdersFrom(delayed, scanner.nextInt());
//			} catch (NoSuchObjectException e) {
//				printer.printFail(e.getMessage());
//			}
//
//		} else {
//			printer.printFail(NO_PERMISSIONS);
//		}
		
	}
}
