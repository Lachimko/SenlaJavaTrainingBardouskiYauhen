package com.bardouski.ui.actions.parents;

import org.apache.log4j.Logger;

import com.bardouski.program.facade.Facade;
import com.bardouski.ui.printer.IPrinter;
import com.bardouski.ui.printer.SysoutPrinter;

public abstract class FacadeAction implements IAction {

	protected Logger logger = null;
	
	protected static IPrinter printer = null;
	protected Facade facade;

	public FacadeAction(Facade facade) {
		this.facade = facade;
		
		if (printer == null) {
			printer = new SysoutPrinter();
		}
	}

}
