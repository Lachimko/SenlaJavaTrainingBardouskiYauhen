package com.bardouski.ui.menuprocessor;

import com.bardouski.ui.actions.parents.IAction;
import com.bardouski.ui.menuelements.IMenu;

public interface IMenuBuilder {
	
	public IMenu createRoot(String rootCaption);
	
	public IMenu createMenuAndSetToParent(String menuCaption, IMenu parent, IAction actionWhichWillExecute);
	
	public IMenu createMenuAndSetToParent(String menuCaption, IMenu parent);

	public IMenu buildMenu();

}
