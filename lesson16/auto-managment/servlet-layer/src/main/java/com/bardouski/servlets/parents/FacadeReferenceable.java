package com.bardouski.servlets.parents;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;

import com.bardouski.config.ProgramProps;
import com.bardouski.facade.IFacade;
import com.bardouski.propertiesholder.PropertiesContext;

public abstract class FacadeReferenceable extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected static final String NO_OBJECT = "{\"error\":\"NoObjectError\"}";
	protected static final String ER_PARAM = "{\"error\":\"IncorrectParamError\"}";
	private static final String CONFIG = "config.properties";
	private static final String CONTEXT = "context.properties";
	
	protected static IFacade facade = null;
	
	public FacadeReferenceable() {

		if (FacadeReferenceable.facade == null) {
			try {
				
				new PropertiesContext(CONTEXT);
				ProgramProps programProps = (ProgramProps) PropertiesContext.getInstance(ProgramProps.class);
				programProps.configure(CONFIG);
				
				facade = (IFacade) PropertiesContext.getInstance(IFacade.class);
			} catch (ClassNotFoundException | FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void reportError(Exception exception, String message, PrintWriter responsePrinter) {
		responsePrinter.print(NO_OBJECT);
		exception.printStackTrace();
	}
	
	

	

}
