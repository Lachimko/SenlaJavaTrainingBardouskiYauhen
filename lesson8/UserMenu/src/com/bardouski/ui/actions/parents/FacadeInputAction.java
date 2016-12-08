package com.bardouski.ui.actions.parents;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.bardouski.propertiesholder.PropertiesContext;
import com.bardouski.ui.scanner.IScanner;

public abstract class FacadeInputAction extends FacadeAction{

	private static final String ERROR_INITIALIZING_SCANNER = "Error initializing scanner!";
	protected IScanner scanner = null;
	
	public FacadeInputAction(ObjectInputStream in, ObjectOutputStream out) {
		
		super(in, out);

		if (scanner == null) {
			try {
				scanner = (IScanner) PropertiesContext.getInstance(IScanner.class);
			} catch (ClassNotFoundException e) {
				System.err.println(ERROR_INITIALIZING_SCANNER);
				logger.error(ERROR_INITIALIZING_SCANNER);
			}
		}
	}

}
