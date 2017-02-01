package com.bardouski.program.controllers.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.bardouski.controllers.services.IOrderService;
import com.bardouski.controllers.stores.IOrderStore;
import com.bardouski.db.connector.DBConnector;
import com.bardouski.dbprocessor.serializator.IResultContainer;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IGarage;
import com.bardouski.model.IOrder;
import com.bardouski.model.impl.Mechanic;
import com.bardouski.model.impl.Order;
import com.bardouski.model.impl.Task;
import com.bardouski.model.impl.WorkPlace;
import com.bardouski.model.impl.enums.OrderStatus;
import com.bardouski.program.controllers.stores.OrderStore;
import com.bardouski.program.controllers.stores.dao.MechanicDAO;
import com.bardouski.program.controllers.stores.dao.OrderDAO;
import com.bardouski.program.controllers.stores.dao.TaskDAO;
import com.bardouski.program.controllers.stores.dao.WorkPlaceDAO;
import com.bardouski.propertiesholder.PropertiesContext;

public class OrderService implements IOrderService{

	private OrderDAO orderDAO = new OrderDAO();
	private TaskDAO taskDAO = new TaskDAO();
	private Connection connection = DBConnector.getConnection();
	private OrderStore orderStore;

	public OrderService() throws ClassNotFoundException{
		orderStore = (OrderStore) PropertiesContext.getInstance(IOrderStore.class);
	}
	
	/**get Instances from DI pool than call methods in transaction scope */
	@Override
	public void addOrder(IOrder order) {
		Savepoint savepoint = null;
		try {
			connection.setAutoCommit(false);
			savepoint = connection.setSavepoint();
			orderDAO.update(connection, (Order) order);
			taskDAO.create(connection, (Task) order.getTask());
			try {
				MechanicDAO mechanicDAO = (MechanicDAO) PropertiesContext.getInstance(MechanicDAO.class);
				mechanicDAO.update(connection, (Mechanic) order.getMechanic());
				WorkPlaceDAO workPlaceDAO = (WorkPlaceDAO) PropertiesContext.getInstance(WorkPlaceDAO.class);
				workPlaceDAO.update(connection, (WorkPlace) order.getWorkPlace());
				connection.commit();
				connection.setAutoCommit(true);
			} catch (ClassNotFoundException e) {
				connection.rollback(savepoint);
			}
		} catch (SQLException e) {
			try {
				connection.rollback(savepoint);
			} catch (SQLException e1) {
			}
		} finally {
			try {
				connection.rollback(savepoint);
				connection.setAutoCommit(true);
			} catch (SQLException e) {
			}
		}
		
	}

	@Override
	public Order getOrder(int orderId) throws NoSuchObjectException {
		return orderDAO.getById(connection, orderId);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderDAO.getAll(connection);
	}
	
	@Override
	public List<Order> getAllOrders(OrderStatus orderStatus) {
		return orderDAO.getAll(connection, orderStatus);
	}
	
	@Override
	public void replaceDatesOfOrdersFrom(int order, int howManyDays) {
		orderDAO.replaceDatesOfOrdersFrom(connection, order, howManyDays);
	}

	@Override
	public void removeOrder(int orderID) throws NoSuchObjectException {
		orderDAO.delete(connection, orderID);
	}

	
	
	
	@Override
	public List<Order> returnOrdersSortedByComparator(Comparator<IOrder> comparator) {
		return orderDAO.returnOrdersSortedByComparator(comparator);
	}

	@Override
	public List<WorkPlace> getFreePlacesInDate(Date date, List<? extends IGarage> garages) {
		return orderDAO.getFreePlacesInDate(date, garages);
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
