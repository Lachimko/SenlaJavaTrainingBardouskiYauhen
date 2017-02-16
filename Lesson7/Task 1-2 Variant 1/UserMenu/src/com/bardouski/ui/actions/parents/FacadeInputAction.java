package com.bardouski.ui.actions.parents;

import com.bardouski.program.facade.IFacade;
import com.bardouski.ui.scanner.MultiScanner;

public abstract class FacadeInputAction extends FacadeAction{

	protected MultiScanner scanner = null;
	
	public FacadeInputAction(IFacade facade) {
		super(facade);
		
		if (scanner == null) {
			scanner = new MultiScanner();
		}
	}

}
