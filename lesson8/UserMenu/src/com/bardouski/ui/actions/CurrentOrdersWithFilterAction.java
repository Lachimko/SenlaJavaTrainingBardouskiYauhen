package com.bardouski.ui.actions;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;

import com.bardouski.facade.IFacade;
import com.bardouski.model.enums.OrderStatus;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class CurrentOrdersWithFilterAction extends FacadeInputAction{

	private static final String SORTING_PARAMETERS = "1.Sort by Request Date\n2.Sort by Complete Date\n3.Sort by Price";

	public CurrentOrdersWithFilterAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@Override
	public String execute() {
		return null;

//		List<Order> list = facade.getAllOrders(OrderStatus.INPROGRESS);
//
//		printer.print(SORTING_PARAMETERS);
//		int sort = scanner.nextInt();
//
//		if (sort == 1){
//			Collections.sort(list, new OrderRequestDateComparator());
//		} else if (sort == 2){
//			Collections.sort(list, new OrderCompleteDateComparator());
//		} else if (sort == 3){
//			Collections.sort(list, new OrderPriceComparator());
//		}
//
//		printer.print(list);
	}

}
