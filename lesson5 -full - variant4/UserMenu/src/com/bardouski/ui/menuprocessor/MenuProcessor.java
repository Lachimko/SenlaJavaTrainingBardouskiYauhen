package com.bardouski.ui.menuprocessor;

import java.util.List;

import com.bardouski.ui.menuelements.MenuElement;
import com.bardouski.ui.printer.SysoutPrinter;
import com.bardouski.ui.scanner.MultiScanner;

public class MenuProcessor {

	private static final String CHOOSE_NEED_POINT_FROM = "Choose need point from: ->";
	private static final String WRONG_VALUE = "WRONG VALUE";
	private MenuElement root;
	private MenuElement current;
	private MultiScanner scanner = new MultiScanner();
	private SysoutPrinter printer = new SysoutPrinter();

	public MenuProcessor(MenuElement root) {
		this.root = root;
		this.current = root;
	}

	/**
	 * Run by menu elements. If menu element has an action - execute it, if no -
	 * get a list of child menus, print them, and set to current this menu.
	 */
	public void process() {

		List<MenuElement> currentListWithChildMenus;

		while (true) {

			currentListWithChildMenus = current.getItems();

			printer.print(CHOOSE_NEED_POINT_FROM + current.getCaption());
			printer.print(printChilds(current));

			if (scanner.hasNextInt()) {

				int i = scanner.nextInt();

				if ((currentListWithChildMenus.size() - 1) < i) {
					printer.printFail(WRONG_VALUE);

				} else {
					MenuElement currentMenuElement = currentListWithChildMenus.get(i);

					if (currentMenuElement.getAction() != null) {
						currentMenuElement.getAction().execute();
					} else {
						// if menu has no actions, go to child menus
						current = currentListWithChildMenus.get(i);
					}
				}

			} else {
				String pushed = scanner.nextLine();
				if (pushed.equals("q")) {
					return;
				}

				if (pushed.equals("b")) {

					if (!current.equals(root)) {
						// if not root menu
						current = current.getParent();
					} else {
						return;
					}
				}
			}
		}
	}

	/**Build String with all elem's children menus*/
	private String printChilds(MenuElement elem) {

		StringBuilder sb = new StringBuilder();
		int i = 0;

		for (MenuElement menuElement : elem.getItems()) {
			sb.append(i++).append(". ").append(menuElement.getCaption()).append("\n");
		}

		return sb.toString();
	}

}
