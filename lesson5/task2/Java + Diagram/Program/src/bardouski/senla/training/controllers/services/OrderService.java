package bardouski.senla.training.controllers.services;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import bardouski.senla.training.controllers.stores.OrderStore;
import bardouski.senla.training.dbprocessor.DBProcessor;
import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.model.Garage;
import bardouski.senla.training.model.Order;
import bardouski.senla.training.model.WorkPlace;
import bardouski.senla.training.model.enums.OrderStatus;

public class OrderService {

	private OrderStore orderStore;

	//Constructors
	public OrderService(DBProcessor dbProcessor) {
		orderStore = new OrderStore(dbProcessor);
	}
	//END Constructors
	
	public void addOrder(Order order) {
		orderStore.addOrder(order);
	}

	public Order getOrder(int orderId) throws NoSuchObjectException {
		return orderStore.getOrder(orderId);
	}

	public List<Order> getAllOrders() {
		return orderStore.getAllOrders();
	}
	
	public List<Order> getAllOrders(OrderStatus orderStatus) {
		return orderStore.getAllOrders(orderStatus);
	}
	
	public void replaceDatesOfOrdersFrom(Order order, int howManyDays) {
		orderStore.replaceDatesOfOrdersFrom(order, howManyDays);
	}

	public void removeOrder(Order order) throws NoSuchObjectException {
		orderStore.removeOrder(order);
	}

	public List<Order> returnOrdersSortedByComparator(Comparator<Order> comparator) {
		return orderStore.returnOrdersSortedByComparator(comparator);
	}

	public List<WorkPlace> getFreePlacesInDate(Date date, List<Garage> garages) {
		return orderStore.getFreePlacesInDate(date, garages);
	}
	
}
