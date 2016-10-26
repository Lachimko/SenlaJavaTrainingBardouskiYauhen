package menu.program.menuelements;

import java.util.ArrayList;
import java.util.List;

import menu.program.actions.parents.IAction;

public class MenuElement implements IMenu {

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

	/* GETTERS/SETTERS */
	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public MenuElement getParent() {
		return parent;
	}

	public void setParent(MenuElement parent) {
		this.parent = parent;
	}

	public IAction getAction() {
		return action;
	}

	public void setAction(IAction action) {
		this.action = action;
	}

	public List<MenuElement> getItems() {
		return items;
	}

}
