package com.bardouski.ui.menuprocessor;

import com.bardouski.config.ProgramProps;
import com.bardouski.program.facade.IFacade;
import com.bardouski.ui.actions.*;
import com.bardouski.ui.actions.parents.IAction;
import com.bardouski.ui.menuelements.IMenu;
import com.bardouski.ui.menuelements.MenuElement;

public class MenuBuilder implements IMenuBuilder {

	private IFacade facade;

	public MenuBuilder() {
	}

	@Override
	public void setFacade(IFacade facade) {
		this.facade = facade;
		
	}
	/**
	 * build and return menu (root point). If some actions are closed by
	 * properties parameters, they signature changed by empty action named
	 * NoAccessAction
	 */
	@Override
	public IMenu buildMenu() {

		IMenu root = createRoot("AutoManagment menu");
		
		IMenu mechanicManager = createMenuAndSetToParent("Mechanic Manager", root);
		// {Mechanic Manager}
		createMenuAndSetToParent("Add Mechanic", mechanicManager, new AddMechanicAction(facade));
		createMenuAndSetToParent("Remove Mechanic", mechanicManager, new RemoveMechanicAction(facade));
		createMenuAndSetToParent("Order executing by Mechanic", mechanicManager,
				new ReturnOrderOfMechanicAction(facade));

		IMenu mechanicSorting = createMenuAndSetToParent("Sort Mechanics", mechanicManager);
		// {Mechanic Manager -> Sort Mechanics}
		createMenuAndSetToParent("FullName", mechanicSorting, new SortMechanicByFullNameAction(facade));
		createMenuAndSetToParent("Current work", mechanicSorting, new SortMechanicByCurrentWorkAction(facade));

		IMenu workPlaceManager = createMenuAndSetToParent("WorkPlace Manager", root);
		// {Workplace Manager}
		createMenuAndSetToParent("Create garage", workPlaceManager, new AddGarageAction(facade));
		createMenuAndSetToParent("Create WorkPlace in garage", workPlaceManager, new AddWorkPlaceInGarageAction(facade, ProgramProps.valueOf("addRemoveWorkpalces")));
		createMenuAndSetToParent("Remove Workplace from garage", workPlaceManager, new RemoveWorkPlaceInGarageAction(facade, ProgramProps.valueOf("addRemoveWorkpalces")));
		
		IMenu orderManager = createMenuAndSetToParent("Order Manager", root);
		// {Order Manager}
		createMenuAndSetToParent("Create Order", orderManager, new CreateOrderAction(facade));
		createMenuAndSetToParent("Remove Order", orderManager, new RemoveOrderAction(facade));
		createMenuAndSetToParent("Mechanic executing Order", orderManager, new ReturnMechanicOfOrderAction(facade));
		createMenuAndSetToParent("Delay Orders", orderManager, new DelayOrdersAction(facade, ProgramProps.valueOf("delayOrders")));
		createMenuAndSetToParent("Free places in date", orderManager, new GetFreePlacesInDateAction(facade));
		createMenuAndSetToParent("Get nearest free date", orderManager, new GetNearestFreeDateAction(facade));

		IMenu ordersStatusChanger = createMenuAndSetToParent("Change Order Status", orderManager);
		// {Order Manager -> Change Order Status}
		createMenuAndSetToParent("Delete Order", ordersStatusChanger, new DeleteOrderAction(facade, ProgramProps.valueOf("removeOrder")));
		
		createMenuAndSetToParent("Cancel Order", ordersStatusChanger, new CancelOrderAction(facade));
		createMenuAndSetToParent("Make Order Ready", ordersStatusChanger, new ReadyOrderAction(facade));

		IMenu ordersFilterViewer = createMenuAndSetToParent("Filter Orders", orderManager);
		// {Order Manager -> Filter Orders}
		createMenuAndSetToParent("View all Orders", ordersFilterViewer, new GetAllOrdersAction(facade));
		createMenuAndSetToParent("View Current Orders, sort by Date", ordersFilterViewer,
				new CurrentOrdersWithFilterAction(facade));
		createMenuAndSetToParent("Advanced Filter", ordersFilterViewer, new AdvancedOrdersOperationsAction(facade));

		// {Order Manager -> View sorted by Date}
		IMenu ordersSorter = createMenuAndSetToParent("View sorted by Date", orderManager);
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
	public IMenu createRoot(String rootCaption) {
		return new MenuElement(rootCaption, null, null);
	}

	/**Create and return menu element and set: link from current to parent; link from parent to current; action*/
	@Override
	public IMenu createMenuAndSetToParent(String menuCaption, IMenu parent, IAction actionWhichWillExecute) {

		IMenu parentTemp = (MenuElement) parent;
		IMenu temp = new MenuElement(menuCaption, actionWhichWillExecute);

		if (parentTemp == null) {
			return null;
		}

		parentTemp.getItems().add(temp);
		temp.setParent(parentTemp);

		return temp;
	}

	/**Create and return menu element and set: link from current to parent; link from parent to current.*/
	@Override
	public IMenu createMenuAndSetToParent(String menuCaption, IMenu parent) {
		return createMenuAndSetToParent(menuCaption, parent, null);
	}



}
