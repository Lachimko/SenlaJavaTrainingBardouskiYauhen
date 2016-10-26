package bardouski.senla.training.comparators;

import java.util.Comparator;

import bardouski.senla.training.model.Order;


public class OrderCompleteDateComparator implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {

		return (o1.getTask().getCompleteDate().before(o1.getTask().getCompleteDate())) ? -1 : 1;
	}

}
