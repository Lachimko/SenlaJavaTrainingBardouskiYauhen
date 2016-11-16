package com.bardouski.ui.menuprocessor;

import com.bardouski.config.AutoManagmentPropertiesHolder;
import com.bardouski.program.facade.Facade;
import com.bardouski.ui.actions.*;
import com.bardouski.ui.actions.parents.IAction;
import com.bardouski.ui.menuelements.IMenu;
import com.bardouski.ui.menuelements.MenuElement;

public class MenuBuilder implements IMenuBuilder {

	private Facade facade;
	private AutoManagmentPropertiesHolder propertiesHolder;

	public MenuBuilder(Facade facade, AutoManagmentPropertiesHolder propertiesHolder) {
		this.facade = facade;

		if (propertiesHolder != null) {
			this.propertiesHolder = propertiesHolder;
		}
	}

	/**
	 * build and return menu (root point). If some actions are closed by
	 * properties parameters, they signature changed by empty action named
	 * NoAccessAction
	 */
	@Override
	public MenuElement buildMenu() {

		MenuElement root = createRoot("AutoManagment menu");
		
		MenuElement mechanicManager = createMenuAndSetToParent("Mechanic Manager", root);
		// {Mechanic Manager}
		createMenuAndSetToParent("Add Mechanic", mechanicManager, new AddMechanicAction(facade));
		createMenuAndSetToParent("Remove Mechanic", mechanicManager, new RemoveMechanicAction(facade));
		createMenuAndSetToParent("Order executing by Mechanic", mechanicManager,
				new ReturnOrderOfMechanicAction(facade));

		MenuElement mechanicSorting = createMenuAndSetToParent("Sort Mechanics", mechanicManager);
		// {Mechanic Manager -> Sort Mechanics}
		createMenuAndSetToParent("FullName", mechanicSorting, new SortMechanicByFullNameAction(facade));
		createMenuAndSetToParent("Current work", mechanicSorting, new SortMechanicByCurrentWorkAction(facade));

		MenuElement workPlaceManager = createMenuAndSetToParent("WorkPlace Manager", root);
		// {Workplace Manager}
		createMenuAndSetToParent("Create garage", workPlaceManager, new AddGarageAction(facade));
		createMenuAndSetToParent("Create WorkPlace in garage", workPlaceManager, new AddWorkPlaceInGarageAction(facade, propertiesHolder));
		createMenuAndSetToParent("Remove Workplace from garage", workPlaceManager, new RemoveWorkPlaceInGarageAction(facade, propertiesHolder));
		
		MenuElement orderManager = createMenuAndSetToParent("Order Manager", root);
		// {Order Manager}
		createMenuAndSetToParent("Create Order", orderManager, new CreateOrderAction(facade));
		createMenuAndSetToParent("Remove Order", orderManager, new RemoveOrderAction(facade));
		createMenuAndSetToParent("Mechanic executing Order", orderManager, new ReturnMechanicOfOrderAction(facade));
		createMenuAndSetToParent("Delay Orders", orderManager, new DelayOrdersAction(facade, propertiesHolder));
		createMenuAndSetToParent("Free places in date", orderManager, new GetFreePlacesInDateAction(facade));
		createMenuAndSetToParent("Get nearest free date", orderManager, new GetNearestFreeDateAction(facade));

		MenuElement ordersStatusChanger = createMenuAndSetToParent("Change Order Status", orderManager);
		// {Order Manager -> Change Order Status}
		createMenuAndSetToParent("Delete Order", ordersStatusChanger, new DeleteOrderAction(facade, propertiesHolder));
		
		createMenuAndSetToParent("Cancel Order", ordersStatusChanger, new CancelOrderAction(facade));
		createMenuAndSetToParent("Make Order Ready", ordersStatusChanger, new ReadyOrderAction(facade));

		MenuElement ordersFilterViewer = createMenuAndSetToParent("Filter Orders", orderManager);
		// {Order Manager -> Filter Orders}
		createMenuAndSetToParent("View all Orders", ordersFilterViewer, new GetAllOrdersAction(facade));
		createMenuAndSetToParent("View Current Orders, sort by Date", ordersFilterViewer,
				new CurrentOrdersWithFilterAction(facade));
		createMenuAndSetToParent("Advanced Filter", ordersFilterViewer, new AdvancedOrdersOperationsAction(facade));

		// {Order Manager -> View sorted by Date}
		MenuElement ordersSorter = createMenuAndSetToParent("View sorted by Date", orderManager);
		createMenuAndSetToParent("By Request Date", ordersSorter, new SortOrdersByRequestDateAction(facade));
		createMenuAndSetToParent("By Complete Date", ordersSorter, new SortOrdersByCompleteDateAction(facade));
		createMenuAndSetToParent("By Start Date", ordersSorter, new SortOrdersByStartDateAction(facade));
		createMenuAndSetToParent("By Price", ordersSorter, new SortOrdersByPriceAction(facade));
		
		// {Root -> Save}
		createMenuAndSetToParent("Save", root, new SaveToDBAction(facade));
		
		// {Root -> Clone Order}
		createMenuAndSetToParent("Clone Order", root, new CloneOrderAction(facade));
		
		return root;
	}

	/**Set root point of menu*/
	@Override
	public MenuElement createRoot(String rootCaption) {
		return new MenuElement(rootCaption, null, null);
	}

	/**Create and return menu element and set: link from current to parent; link from parent to current; action*/
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

	/**Create and return menu element and set: link from current to parent; link from parent to current.*/
	@Override
	public MenuElement createMenuAndSetToParent(String menuCaption, IMenu parent) {
		return createMenuAndSetToParent(menuCaption, parent, null);
	}

}
