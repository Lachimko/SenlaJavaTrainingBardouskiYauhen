package menu.program.menuprocessor;

import bardouski.senla.training.facade.Facade;
import menu.program.actions.*;
import menu.program.actions.parents.IAction;
import menu.program.menuelements.IMenu;
import menu.program.menuelements.MenuElement;

public class MenuBuilder implements IMenuBuilder {

	//private MenuElement root;
	private Facade facade;

	public MenuBuilder(Facade facade) {
		this.facade = facade;
	}

	/** build and return menu (root point) */
	@Override
	public MenuElement buildMenu() {

		MenuElement root = createRoot("AutoManagment root menu");

		MenuElement mechanicManager = createMenuAndSetToParent("Mechanic Manager", root);
		MenuElement workPlaceManager = createMenuAndSetToParent("WorkPlace Manager", root);
		MenuElement orderManager = createMenuAndSetToParent("Order Manager", root);

		// Mechanic Manager menu
		createMenuAndSetToParent("Add Mechanic", mechanicManager, new ActionAddMechanic(facade));
		createMenuAndSetToParent("Remove Mechanic", mechanicManager, new ActionRemoveMechanic(facade));
		createMenuAndSetToParent("Order executing by Mechanic", mechanicManager, new ActionReturnOrderOfMechanic(facade));

		MenuElement mechanicSorting = createMenuAndSetToParent("Sort Mechanics", mechanicManager);

		createMenuAndSetToParent("FullName", mechanicSorting, new ActionSortMechanicByFullName(facade));
		createMenuAndSetToParent("Current work", mechanicSorting, new ActionSortMechanicByCurrentWork(facade));

		// WorkPlace Manager menu
		createMenuAndSetToParent("Create garage", workPlaceManager, new ActionAddGarage(facade));
		createMenuAndSetToParent("Create WorkPlace in garage", workPlaceManager, new ActionAddWorkPlaceInGarage(facade));
		createMenuAndSetToParent("Remove Workplace from garage", workPlaceManager, new ActionRemoveWorkPlaceInGarage(facade));

		// Order Manager menu

		//MenuElement orderManagerOptions = new MenuElement("Order Options", orderManager, null);

		return root;
	}

	@Override
	public MenuElement createRoot(String rootCaption) {

		return new MenuElement(rootCaption, null, null);
	}

	@Override
	public MenuElement createMenuAndSetToParent(String menuCaption, IMenu parent, IAction actionWhichWillExecute) {

		MenuElement parentTemp = (MenuElement) parent;

		MenuElement temp = new MenuElement(menuCaption, actionWhichWillExecute);

		if (parentTemp == null) {
			return null;
		}

		parentTemp.getItems().add(temp);
		temp.setParent(parentTemp);

		return temp;
	}

	@Override
	public MenuElement createMenuAndSetToParent(String menuCaption, IMenu parent) {

		return createMenuAndSetToParent(menuCaption, parent, null);
	}

}
