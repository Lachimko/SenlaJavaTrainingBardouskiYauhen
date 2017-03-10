package com.bardouski.servlets.parents;

import javax.servlet.http.HttpServlet;

import com.bardouski.facade.IFacade;
import com.bardouski.propertiesholder.PropertiesContext;

public abstract class FacadeReferenceable extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected static IFacade facade = null;
	
	public FacadeReferenceable() {

		if (FacadeReferenceable.facade == null) {
			try {
				facade = (IFacade) PropertiesContext.getInstance(IFacade.class);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	

	

}
