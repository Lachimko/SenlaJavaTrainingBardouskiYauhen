package com.bardouski.ui.actions;

import com.bardouski.program.facade.IFacade;
import com.bardouski.ui.actions.parents.FacadeAction;

public class SaveToDBAction extends FacadeAction{

	public SaveToDBAction(IFacade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		facade.saveToFile();
	}

}
