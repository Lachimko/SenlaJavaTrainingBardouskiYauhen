package com.bardouski.ui.actions.parents;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.bardouski.propertiesholder.PropertiesContext;
import com.bardouski.requests.RequestContainer;
import com.bardouski.ui.printer.IPrinter;

import org.apache.log4j.Logger;

public abstract class FacadeAction implements IAction {

	private static final String ERROR_INITIALIZING_PRINTER = "Error initializing Printer!";

	protected RequestContainer requestContainer = new RequestContainer();
	protected ObjectInputStream in;
	protected ObjectOutputStream out;
	protected Logger logger;
	protected static IPrinter printer;

	public FacadeAction(ObjectInputStream in, ObjectOutputStream out) {

		this.in = in;
		this.out = out;

		if (printer == null) {
			try {
				printer = (IPrinter) PropertiesContext.getInstance(IPrinter.class);
			} catch (ClassNotFoundException e) {
				System.err.println(ERROR_INITIALIZING_PRINTER);
				logger = Logger.getLogger(FacadeAction.class);
				logger.error(ERROR_INITIALIZING_PRINTER);
			}
		}
	}

}
