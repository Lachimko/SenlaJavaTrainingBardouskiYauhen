package menu.program.menuprocessor;

import bardouski.senla.training.facade.Facade;
import menu.program.actions.*;
import menu.program.actions.parents.IAction;
import menu.program.menuelements.IMenu;
import menu.program.menuelements.MenuElement;

public class MenuBuilder implements IMenuBuilder {

	private Facade facade;

	public MenuBuilder(Facade facade) {
		this.facade = facade;
	}

	/** build and return menu (root point) */
	@Override
	public MenuElement buildMenu() {

		MenuElement root = createRoot("AutoManagment menu");

		MenuElement mechanicManager = createMenuAndSetToParent("Mechanic Manager", root);
		// {Mechanic Manager}
		createMenuAndSetToParent("Add Mechanic", mechanicManager, new ActionAddMechanic(facade));
		createMenuAndSetToParent("Remove Mechanic", mechanicManager, new ActionRemoveMechanic(facade));
		createMenuAndSetToParent("Order executing by Mechanic", mechanicManager, new ActionReturnOrderOfMechanic(facade));
		
		MenuElement mechanicSorting = createMenuAndSetToParent("Sort Mechanics", mechanicManager);
		// {Mechanic Manager -> Sort Mechanics}
		createMenuAndSetToParent("FullName", mechanicSorting, new ActionSortMechanicByFullName(facade));
		createMenuAndSetToParent("Current work", mechanicSorting, new ActionSortMechanicByCurrentWork(facade));

		MenuElement workPlaceManager = createMenuAndSetToParent("WorkPlace Manager", root);
		// {Workplace Manager}
		createMenuAndSetToParent("Create garage", workPlaceManager, new ActionAddGarage(facade));
		createMenuAndSetToParent("Create WorkPlace in garage", workPlaceManager, new ActionAddWorkPlaceInGarage(facade));
		createMenuAndSetToParent("Remove Workplace from garage", workPlaceManager, new ActionRemoveWorkPlaceInGarage(facade));
		
		MenuElement orderManager = createMenuAndSetToParent("Order Manager", root);
		// {Order Manager}
		createMenuAndSetToParent("Create Order", orderManager, new ActionCreateOrder(facade));
		createMenuAndSetToParent("Remove Order", orderManager, new ActionRemoveOrder(facade));
		createMenuAndSetToParent("Mechanic executing Order", orderManager, new ActionReturnMechanicOfOrder(facade));
		createMenuAndSetToParent("Delay Orders", orderManager, new ActionDelayOrders(facade));
		createMenuAndSetToParent("Free places in date", orderManager, new ActionGetFreePlacesInDate(facade));
		createMenuAndSetToParent("Get nearest free date", orderManager, new ActionGetNearestFreeDate(facade));
		
		MenuElement ordersStatusChanger = createMenuAndSetToParent("Change Order Status", orderManager);
		// {Order Manager -> Change Order Status}
		createMenuAndSetToParent("Delete Order", ordersStatusChanger, new ActionDeleteOrder(facade));
		createMenuAndSetToParent("Cancel Order", ordersStatusChanger, new ActionCancelOrder(facade));
		createMenuAndSetToParent("Make Order Ready", ordersStatusChanger, new ActionReadyOrder(facade));
		
		MenuElement ordersFilterViewer = createMenuAndSetToParent("Filter Orders", orderManager);
		// {Order Manager -> Filter Orders}
		createMenuAndSetToParent("View all Orders", ordersFilterViewer, new ActionGetAllOrders(facade));
		createMenuAndSetToParent("View Current Orders, sort by Date", ordersFilterViewer , new ActionCurrentOrdersWithFilter(facade));
		createMenuAndSetToParent("Advanced Filter", ordersFilterViewer, new ActionAdvancedOrdersOperations(facade));
		
		// {Order Manager -> View sorted by Date}
		MenuElement ordersSorter = createMenuAndSetToParent("View sorted by Date", orderManager);
		createMenuAndSetToParent("By Request Date", ordersSorter, new ActionSortOrdersByRequestDate(facade));
		createMenuAndSetToParent("By Complete Date", ordersSorter, new ActionSortOrdersByCompleteDate(facade));
		createMenuAndSetToParent("By Start Date", ordersSorter, new ActionSortOrdersByStartDate(facade));
		createMenuAndSetToParent("By Price", ordersSorter, new ActionSortOrdersByPrice(facade));
		
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
