package com.bardouski.ui.actions;

import java.util.Collections;
import java.util.List;

import com.bardouski.program.comparators.OrderCompleteDateComparator;
import com.bardouski.program.comparators.OrderPriceComparator;
import com.bardouski.program.comparators.OrderRequestDateComparator;
import com.bardouski.program.facade.Facade;
import com.bardouski.program.model.Order;
import com.bardouski.program.model.enums.OrderStatus;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class CurrentOrdersWithFilterAction extends FacadeInputAction{

	private static final String SORTING_PARAMETERS = "1.Sort by Request Date\n2.Sort by Complete Date\n3.Sort by Price";

	public CurrentOrdersWithFilterAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		List<Order> list = facade.getAllOrders(OrderStatus.INPROGRESS);
		
		printer.print(SORTING_PARAMETERS);
		int sort = scanner.nextInt();
		
		if (sort == 1){
			Collections.sort(list, new OrderRequestDateComparator());
		} else if (sort == 2){
			Collections.sort(list, new OrderCompleteDateComparator());
		} else if (sort == 3){
			Collections.sort(list, new OrderPriceComparator());
		}
		
		printer.print(list);
	}

}
