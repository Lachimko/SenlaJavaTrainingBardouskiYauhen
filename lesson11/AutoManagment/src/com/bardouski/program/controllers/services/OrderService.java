package com.bardouski.program.controllers.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Date;
import java.util.List;

import com.bardouski.controllers.services.IOrderService;
import com.bardouski.db.connector.DBConnector;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IOrder;
import com.bardouski.model.impl.Mechanic;
import com.bardouski.model.impl.Order;
import com.bardouski.model.impl.Task;
import com.bardouski.model.impl.WorkPlace;
import com.bardouski.model.impl.enums.OrderStatus;
import com.bardouski.program.controllers.stores.dao.MechanicDAO;
import com.bardouski.program.controllers.stores.dao.OrderDAO;
import com.bardouski.program.controllers.stores.dao.TaskDAO;
import com.bardouski.program.controllers.stores.dao.WorkPlaceDAO;
import com.bardouski.propertiesholder.PropertiesContext;

public class OrderService implements IOrderService {

	private OrderDAO orderDAO;
	private TaskDAO taskDAO;
	private DBConnector dbConnector;

	public OrderService() throws ClassNotFoundException {
		orderDAO = (OrderDAO) PropertiesContext.getInstance(OrderDAO.class);
		taskDAO = (TaskDAO) PropertiesContext.getInstance(TaskDAO.class);
		dbConnector = (DBConnector) PropertiesContext.getInstance(DBConnector.class);
	}

	/** get Instances from DI pool than call methods in transaction scope */
	@Override
	public void addOrder(IOrder order) {
		Connection connection = dbConnector.getConnection();
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
				connection.setAutoCommit(true);
			} catch (SQLException e) {
			}
		}

	}

	@Override
	public Order getOrder(int orderId) throws NoSuchObjectException {
		return orderDAO.getById(dbConnector.getConnection(), orderId);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderDAO.getAll(dbConnector.getConnection());
	}

	@Override
	public List<Order> getAllOrders(OrderStatus orderStatus) {
		return orderDAO.getAll(dbConnector.getConnection(), orderStatus);
	}

	@Override
	public void replaceDatesOfOrdersFrom(int order, int howManyDays) {
		orderDAO.sortOrdersByCompleteDateAction(dbConnector.getConnection());
	}

	@Override
	public void removeOrder(int orderID) throws NoSuchObjectException {
		orderDAO.delete(dbConnector.getConnection(), orderID);
	}

	@Override
	public List<WorkPlace> getFreePlacesInDate(Date date) {
		return orderDAO.getFreePlacesInDate(dbConnector.getConnection(), date);
	}

	public List<Order> sortOrdersByCompleteDateAction() {
		return orderDAO.sortOrdersByCompleteDateAction(dbConnector.getConnection());
	}

	public List<Order> sortOrdersByPriceAction() {
		return orderDAO.sortOrdersByPriceAction(dbConnector.getConnection());
	}

	public List<Order> sortOrdersByRequestDateAction() {
		return orderDAO.sortOrdersByRequestDateAction(dbConnector.getConnection());
	}

	public List<Order> sortOrdersByStartDateAction() {
		return orderDAO.sortOrdersByStartDateAction(dbConnector.getConnection());
	}

}
