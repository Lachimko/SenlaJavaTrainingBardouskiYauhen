package bardouski.senla.training.comparators;

import java.util.Comparator;

import bardouski.senla.training.model.Order;

public class OrderIdComparator implements Comparator<Order>{

	@Override
	public int compare(Order o1, Order o2) {
		return (o1.getId() < o2.getId()) ? -1 : 1;
	}

}
