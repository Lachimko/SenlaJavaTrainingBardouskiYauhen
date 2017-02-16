package com.bardouski.ui.menuelements;

import java.util.List;

import com.bardouski.ui.actions.parents.IAction;

public interface IMenu {

	String getCaption();

	void setCaption(String caption);

	IMenu getParent();

	void setParent(IMenu parent);

	IAction getAction();

	void setAction(IAction action);

	List<? extends IMenu> getItems();

}
