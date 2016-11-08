package menu.program.actions;

import java.util.Collections;
import java.util.List;

import bardouski.senla.training.comparators.OrderCompleteDateComparator;
import bardouski.senla.training.comparators.OrderPriceComparator;
import bardouski.senla.training.comparators.OrderRequestDateComparator;
import bardouski.senla.training.facade.Facade;
import bardouski.senla.training.model.Order;
import bardouski.senla.training.model.enums.OrderStatus;
import menu.program.actions.parents.FacadeInputAction;

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
