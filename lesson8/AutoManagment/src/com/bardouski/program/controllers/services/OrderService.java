package com.bardouski.program.controllers.services;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.bardouski.controllers.services.IOrderService;
import com.bardouski.controllers.stores.IOrderStore;
import com.bardouski.dbprocessor.serializator.IResultContainer;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IGarage;
import com.bardouski.model.IOrder;
import com.bardouski.model.enums.OrderStatus;
import com.bardouski.program.controllers.stores.OrderStore;
import com.bardouski.program.model.Order;
import com.bardouski.program.model.WorkPlace;
import com.bardouski.propertiesholder.PropertiesContext;

public class OrderService implements IOrderService{

	private OrderStore orderStore;

	public OrderService() throws ClassNotFoundException{
		orderStore = (OrderStore) PropertiesContext.getInstance(IOrderStore.class);
	}
	
	@Override
	public void addOrder(IOrder order) {
		orderStore.addOrder(order);
	}

	@Override
	public Order getOrder(int orderId) throws NoSuchObjectException {
		return orderStore.getOrder(orderId);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderStore.getAllOrders();
	}
	
	@Override
	public List<Order> getAllOrders(OrderStatus orderStatus) {
		return orderStore.getAllOrders(orderStatus);
	}
	
	@Override
	public void replaceDatesOfOrdersFrom(IOrder order, int howManyDays) {
		orderStore.replaceDatesOfOrdersFrom(order, howManyDays);
	}

	@Override
	public void removeOrder(IOrder order) throws NoSuchObjectException {
		orderStore.removeOrder(order);
	}

	@Override
	public List<Order> returnOrdersSortedByComparator(Comparator<IOrder> comparator) {
		return orderStore.returnOrdersSortedByComparator(comparator);
	}

	@Override
	public List<WorkPlace> getFreePlacesInDate(Date date, List<? extends IGarage> garages) {
		return orderStore.getFreePlacesInDate(date, garages);
	}

	@Override
	public void saveToFile(IResultContainer resultContainer) {
		orderStore.saveToFile(resultContainer);
	}

	/**Return new unique Id from OrderStore. Use for getting clone*/
	@Override
	public int getNextOrderId(){
		return orderStore.getNextId();
	}
	
}
