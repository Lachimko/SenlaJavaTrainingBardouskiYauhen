package com.bardouski.ui.menuelements;

import java.util.ArrayList;
import java.util.List;

import com.bardouski.ui.actions.parents.IAction;

public class MenuElement implements IMenu{

	private String caption;
	private MenuElement parent;
	private List<MenuElement> items = new ArrayList<>();
	private IAction action;

	public MenuElement(String caption, IAction action) {
		this.caption = caption;
		this.action = action;
	}

	public MenuElement(String caption, MenuElement parent, IAction action) {
		this.caption = caption;
		this.action = action;
		this.parent = parent;
	}

	@Override
	public String getCaption() {
		return caption;
	}

	@Override
	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Override
	public MenuElement getParent() {
		return (MenuElement) parent;
	}

	@Override
	public void setParent(IMenu parent) {
		this.parent = (MenuElement) parent;
	}

	public IAction getAction() {
		return action;
	}

	@Override
	public void setAction(IAction action) {
		this.action = action;
	}

	@Override
	public List<MenuElement> getItems() {
		return items;
	}

}
