package com.bardouski.program.controllers.services;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.bardouski.program.controllers.stores.IOrderStore;
import com.bardouski.program.controllers.stores.OrderStore;
import com.bardouski.program.dbprocessor.serializator.IResultContainer;
import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.model.Garage;
import com.bardouski.program.model.Order;
import com.bardouski.program.model.WorkPlace;
import com.bardouski.program.model.enums.OrderStatus;
import com.bardouski.propertiesholder.PropertiesContext;

public class OrderService implements IOrderService{

	private IOrderStore orderStore;

	//Constructors

	public OrderService() throws ClassNotFoundException{
		orderStore = (OrderStore) PropertiesContext.getInstance(IOrderStore.class);
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

	public void saveToFile(IResultContainer resultContainer) {
		orderStore.saveToFile(resultContainer);
	}

	/**Return new unique Id from OrderStore. Use for getting clone*/
	public int getNextOrderId(){
		return orderStore.getNextId();
	}
	
}