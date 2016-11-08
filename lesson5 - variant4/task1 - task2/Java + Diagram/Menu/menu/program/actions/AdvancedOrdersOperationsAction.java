package menu.program.actions;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import bardouski.senla.training.comparators.OrderCompleteDateComparator;
import bardouski.senla.training.comparators.OrderPriceComparator;
import bardouski.senla.training.comparators.OrderRequestDateComparator;
import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Order;
import bardouski.senla.training.model.enums.OrderStatus;
import menu.program.actions.parents.FacadeInputAction;

public class AdvancedOrdersOperationsAction extends FacadeInputAction {

	private static final String NO_RESULT = "No result";
	private static final String PARSE_ERROR = "Wrong Date Pattern";
	private static final String TO_DATE = "To Date (dd.MM.yyyy):";
	private static final String FROM_DATE = "From Date (dd.MM.yyyy):";
	private static final String ORDERS_PICKER = "1.Take Ready Orders\n2.Take Deleted Orders\n3.Take Cancelled Orders";
	private static final String SORTING_PARAMETERS = "1.Sort by Request Date\n2.Sort by Complete Date\n3.Sort by Price";
	
	public AdvancedOrdersOperationsAction(Facade facade) {
		super(facade);
	}

	@Override
	public void execute() {

		List<Order> list = new ArrayList<>();
		int chosen;
		printer.print(ORDERS_PICKER);
		chosen = scanner.nextInt();

		if (chosen == 1) {
			list = facade.getAllOrders(OrderStatus.READY);
		} else if (chosen == 2) {
			list = facade.getAllOrders(OrderStatus.DELETED);
		} else if (chosen == 3) {
			list = facade.getAllOrders(OrderStatus.CANCELLED);
		}

		if (!list.isEmpty()) {
			try {
				printer.print(FROM_DATE);
				Date start = scanner.parseDate(scanner.nextLine());
				printer.print(TO_DATE);
				Date end = scanner.parseDate(scanner.nextLine());

				List<Order> temp = new ArrayList<>();
				for (Order order : list) {
					if (order.getTask().getRequestDate().after(start) && order.getTask().getRequestDate().before(end)) {
						temp.add(order);
					}
				}
				list = temp;
			} catch (ParseException e) {
				logger.error(e.getMessage());
				printer.printFail(PARSE_ERROR);
			}
		} 
		
		if(!list.isEmpty()){
			printer.print(SORTING_PARAMETERS);
			chosen = scanner.nextInt();
			
			if (chosen == 1){
				Collections.sort(list, new OrderRequestDateComparator());
			} else if (chosen == 2){
				Collections.sort(list, new OrderCompleteDateComparator());
			} else if (chosen == 3){
				Collections.sort(list, new OrderPriceComparator());
			}
			
			printer.print(list);
			
		} else {
			printer.print(NO_RESULT);
		}
	}

}
