package com.bardouski.ui.actions;

import com.bardouski.program.facade.Facade;
import com.bardouski.ui.actions.parents.FacadeAction;

public class SaveToDBAction extends FacadeAction{

	public SaveToDBAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {
		facade.saveToFile();
	}

}
