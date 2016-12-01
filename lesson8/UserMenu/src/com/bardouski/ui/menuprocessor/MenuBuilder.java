package com.bardouski.ui.menuprocessor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.bardouski.config.ProgramProps;
import com.bardouski.ui.actions.*;
import com.bardouski.ui.actions.parents.IAction;
import com.bardouski.ui.menuelements.IMenu;
import com.bardouski.ui.menuelements.MenuElement;

public class MenuBuilder implements IMenuBuilder {

	ObjectInputStream in;
	ObjectOutputStream out;

	public MenuBuilder() {
	}

	@Override
	public void setStreams(ObjectInputStream in, ObjectOutputStream out) {
		this.in = in;
		this.out = out;
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
		createMenuAndSetToParent("Add Mechanic", mechanicManager, new AddMechanicAction(in, out));
		createMenuAndSetToParent("Remove Mechanic", mechanicManager, new RemoveMechanicAction(in, out));
		createMenuAndSetToParent("Order executing by Mechanic", mechanicManager,
				new ReturnOrderOfMechanicAction(in, out));

		MenuElement mechanicSorting = createMenuAndSetToParent("Sort Mechanics", mechanicManager);
		// {Mechanic Manager -> Sort Mechanics}
		createMenuAndSetToParent("FullName", mechanicSorting, new SortMechanicByFullNameAction(in, out));
		createMenuAndSetToParent("Current work", mechanicSorting, new SortMechanicByCurrentWorkAction(in, out));

		MenuElement workPlaceManager = createMenuAndSetToParent("WorkPlace Manager", root);
		// {Workplace Manager}
		createMenuAndSetToParent("Create garage", workPlaceManager, new AddGarageAction(in, out));
		createMenuAndSetToParent("Create WorkPlace in garage", workPlaceManager, new AddWorkPlaceInGarageAction(in, out, ProgramProps.valueOf("addRemoveWorkpalces")));
		createMenuAndSetToParent("Remove Workplace from garage", workPlaceManager, new RemoveWorkPlaceInGarageAction(in, out, ProgramProps.valueOf("addRemoveWorkpalces")));
		
		MenuElement orderManager = createMenuAndSetToParent("Order Manager", root);
		// {Order Manager}
		createMenuAndSetToParent("Create Order", orderManager, new CreateOrderAction(in, out));
		createMenuAndSetToParent("Remove Order", orderManager, new RemoveOrderAction(in, out));
		createMenuAndSetToParent("Mechanic executing Order", orderManager, new ReturnMechanicOfOrderAction(in, out));
		createMenuAndSetToParent("Delay Orders", orderManager, new DelayOrdersAction(in, out, ProgramProps.valueOf("delayOrders")));
		createMenuAndSetToParent("Free places in date", orderManager, new GetFreePlacesInDateAction(in, out));
		createMenuAndSetToParent("Get nearest free date", orderManager, new GetNearestFreeDateAction(in, out));

		MenuElement ordersStatusChanger = createMenuAndSetToParent("Change Order Status", orderManager);
		// {Order Manager -> Change Order Status}
		createMenuAndSetToParent("Delete Order", ordersStatusChanger, new DeleteOrderAction(in, out, ProgramProps.valueOf("removeOrder")));
		
		createMenuAndSetToParent("Cancel Order", ordersStatusChanger, new CancelOrderAction(in, out));
		createMenuAndSetToParent("Make Order Ready", ordersStatusChanger, new ReadyOrderAction(in, out));

		MenuElement ordersFilterViewer = createMenuAndSetToParent("Filter Orders", orderManager);
		// {Order Manager -> Filter Orders}
		createMenuAndSetToParent("View all Orders", ordersFilterViewer, new GetAllOrdersAction(in, out));
		createMenuAndSetToParent("View Current Orders, sort by Date", ordersFilterViewer,
				new CurrentOrdersWithFilterAction(in, out));
		createMenuAndSetToParent("Advanced Filter", ordersFilterViewer, new AdvancedOrdersOperationsAction(in, out));

		// {Order Manager -> View sorted by Date}
		MenuElement ordersSorter = createMenuAndSetToParent("View sorted by Date", orderManager);
		createMenuAndSetToParent("By Request Date", ordersSorter, new SortOrdersByRequestDateAction(in, out));
		createMenuAndSetToParent("By Complete Date", ordersSorter, new SortOrdersByCompleteDateAction(in, out));
		createMenuAndSetToParent("By Start Date", ordersSorter, new SortOrdersByStartDateAction(in, out));
		createMenuAndSetToParent("By Price", ordersSorter, new SortOrdersByPriceAction(in, out));
		
		// {Root -> Save}
		createMenuAndSetToParent("Save", root, new SaveToDBAction(in, out));
		
		// {Root -> Clone Order}
		createMenuAndSetToParent("Clone Order", root, new CloneOrderAction(in, out));
		
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
