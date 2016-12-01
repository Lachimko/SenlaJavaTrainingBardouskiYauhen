package com.bardouski.ui.menuprocessor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bardouski.ui.actions.parents.IAction;
import com.bardouski.ui.menuelements.IMenu;

public interface IMenuBuilder {
	
	public IMenu createRoot(String rootCaption);
	
	public IMenu createMenuAndSetToParent(String menuCaption, IMenu parent, IAction actionWhichWillExecute);
	
	public IMenu createMenuAndSetToParent(String menuCaption, IMenu parent);

	public IMenu buildMenu();
	
	public void setStreams(ObjectInputStream in, ObjectOutputStream out);

}
