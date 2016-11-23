package com.bardouski.ui.menuprocessor;

public interface IMenuProcessor {

	/**
	 * Run by menu elements. If menu element has an action - execute it, if no -
	 * get a list of child menus, print them, and set to current this menu.
	 */
	void process();

}
