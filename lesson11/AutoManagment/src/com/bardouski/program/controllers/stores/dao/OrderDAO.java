package com.bardouski.program.controllers.stores.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.bardouski.model.IGarage;
import com.bardouski.model.IOrder;
import com.bardouski.model.impl.Order;
import com.bardouski.model.impl.WorkPlace;
import com.bardouski.model.impl.enums.OrderStatus;

public class OrderDAO extends AbstractDAO<Order> {

	private static final String SELECT_FROM_ORDER_STATUS = "select * from `order` where status = ?";
	private static final String UPDATE_ORDER_STATUS = "update `order` set id_mechanic=?, id_workplace=?, id_task=?, status=? where id_order=?";
	private static final String SELECT_ORDER_BY_ID = "select * from `order` where id_order = ?";
	private static final String SELECT_ALL_FROM_ORDER = "select * from `order`";
	private static final String CREATE_ORDER = "insert into `order`(id_mechanic, id_workplace, id_task) value (?, ?, ?)";
	
	
//	public void create(Connection connection, IOrder order) {
//		int task_id = order.getTask().getId();
//		int mechanic_id = order.getMechanic().getId();
//		int workplace_id = order.getWorkPlace().getId();
//
//		try {
//			connection.setAutoCommit(false);
//
//			PreparedStatement statement = connection.prepareStatement(
//					"insert into `order`(id_mechanic, id_workplace, id_task, status) values (?,?,?,?)");
//			statement.setInt(1, mechanic_id);
//			statement.setInt(2, workplace_id);
//			statement.setInt(3, task_id);
//			statement.setString(4, order.getOrderStatus().toString());
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// update mechanic order_id
//		// update workplace order_id
//		// replace transaction to service levels
//		// end transaction
//	}

	@Override
	public String injectUpdateQuery() {
		return UPDATE_ORDER_STATUS;
	}

	@Override
	public String injectGetAllQuery() {
		return SELECT_ALL_FROM_ORDER;
	}

	@Override
	public String injectGetByIdQuery() {
		return SELECT_ORDER_BY_ID;
	}

	@Override
	public String injectInsertQuery() {
		return CREATE_ORDER;
	}
	
	public List<Order> getAll(Connection connection, OrderStatus orderStatus) {

		List<Order> tempList = new ArrayList<>();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_FROM_ORDER_STATUS);
			while (resultSet.next()) {
				tempList.add(parseEntity(resultSet));
			}
			return tempList;
		} catch (SQLException e) {
			logger.error(SQL_ERROR);
			return null;
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public void replaceDatesOfOrdersFrom(Connection connection, int order, int howManyDays) {
		// TODO Auto-generated method stub

	}

	@Override
	public Order parseEntity(ResultSet resultSet) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String injectDeleteQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WorkPlace> getFreePlacesInDate(Date date, List<? extends IGarage> garages) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Order> returnOrdersSortedByComparator(Comparator<IOrder> comparator) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prepareUpdateStatement(PreparedStatement statement, Order object) throws SQLException {
		prepareInsertStatement(statement, object);
		statement.setString(4, object.getOrderStatus().toString());
		statement.setInt(5, object.getId());
	}

	@Override
	public void prepareInsertStatement(PreparedStatement statement, Order object) throws SQLException {
		statement.setInt(1, object.getMechanic().getId());
		statement.setInt(2, object.getWorkPlace().getId());
		statement.setInt(3, object.getTask().getId());
	}

}
