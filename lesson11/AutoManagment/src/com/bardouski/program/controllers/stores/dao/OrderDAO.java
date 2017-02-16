package com.bardouski.program.controllers.stores.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bardouski.controllers.dao.AbstractDAO;
import com.bardouski.controllers.dao.IOrderDAO;
import com.bardouski.model.impl.Order;
import com.bardouski.model.impl.WorkPlace;
import com.bardouski.model.impl.enums.OrderStatus;
import com.bardouski.mysqlparser.MySQLDateParser;
import com.bardouski.propertiesholder.PropertiesContext;

public class OrderDAO extends AbstractDAO<Order> implements IOrderDAO {

	private static final String ID_GARAGE = "id_garage";
	private static final String FULL_NAME = "full_name";
	private static final String TO_DO = "to_do";
	private static final String ID_ORDER = "id_order";
	private static final String START_DATE = "start_date";
	private static final String REQUEST_DATE = "request_date";
	private static final String PRICE = "price";
	private static final String COMPLETE_DATE = "complete_date";
	private static final String DATE_RANGE = " where start_date < ? and complete_date > ?";
	private static final String SELECT_FROM_ORDER_STATUS = "select * from `order` where status = ?";
	private static final String UPDATE_ORDER_STATUS = "update `order` set id_mechanic=?, id_workplace=?, id_task=?, status=? where id_order=?";
	private static final String SELECT_ORDER_BY_ID = "select * from `order` where id_order = ?";
	private static final String SELECT_ALL_FROM_ORDER = "select * from `order` as o join task as t on o.id_task = t.id_task left join workplace as w on o.id_order = w.id_order left join mechanic as m on o.id_order = m.id_order";
	private static final String CREATE_ORDER = "insert into `order`(id_mechanic, id_workplace, id_task) value (?, ?, ?)";
	private static final String DELETE_ORDER = "delete from `order` where id_order = ?";
	
	@Override
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

	@Override
	public List<Order> sortOrdersByCompleteDateAction(Connection connection) {
		return getAll(connection, COMPLETE_DATE);
	}

	@Override
	public List<Order> sortOrdersByPriceAction(Connection connection) {
		return getAll(connection, PRICE);
	}

	@Override
	public List<Order> sortOrdersByRequestDateAction(Connection connection) {
		return getAll(connection, REQUEST_DATE);
	}

	@Override
	public List<Order> sortOrdersByStartDateAction(Connection connection) {
		return getAll(connection, START_DATE);
	}
	
	@Override
	public List<WorkPlace> getFreePlacesInDate(Connection connection, Date date) {
		
		WorkPlaceDAO workPlaceDAO = null;
		try {
			workPlaceDAO = (WorkPlaceDAO) PropertiesContext.getInstance(WorkPlaceDAO.class);
		} catch (ClassNotFoundException e1) {
			logger.error(e1.getMessage());
		}
		List<WorkPlace> tempList = new ArrayList<>();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SELECT_ALL_FROM_ORDER + DATE_RANGE);
			statement.setString(1, MySQLDateParser.generateStringFromDate(date));
			statement.setString(2, MySQLDateParser.generateStringFromDate(date));
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				tempList.add(workPlaceDAO.parseEntity(resultSet));
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

	@Override
	protected void prepareUpdateStatement(PreparedStatement statement, Order object) throws SQLException {
		prepareInsertStatement(statement, object);
		statement.setString(4, object.getOrderStatus().toString());
		statement.setInt(5, object.getId());
	}

	@Override
	protected void prepareInsertStatement(PreparedStatement statement, Order object) throws SQLException {
		statement.setInt(1, object.getMechanic().getId());
		statement.setInt(2, object.getWorkPlace().getId());
		statement.setInt(3, object.getTask().getId());
	}

	@Override
	protected Order parseEntity(ResultSet resultSet) {

		try {
			Order temp = new Order();
			temp.setId(resultSet.getInt(ID_ORDER));
			temp.setOrderStatus(OrderStatus.valueOf(resultSet.getString("status")));

			if (resultSet.findColumn(TO_DO) > 0) {
				TaskDAO taskDAO = (TaskDAO) PropertiesContext.getInstance(TaskDAO.class);
				temp.setTask(taskDAO.parseEntity(resultSet));
			}

			if (resultSet.findColumn(FULL_NAME) > 0) {
				MechanicDAO mechanicDAO = (MechanicDAO) PropertiesContext.getInstance(MechanicDAO.class);
				temp.setMechanic(mechanicDAO.parseEntity(resultSet));
			}

			if (resultSet.findColumn(ID_GARAGE) > 0) {
				WorkPlaceDAO workPlaceDAO = (WorkPlaceDAO) PropertiesContext.getInstance(WorkPlaceDAO.class);
				temp.setWorkPlace(workPlaceDAO.parseEntity(resultSet));
			}

			return temp;
		} catch (SQLException | ClassNotFoundException e) {
			logger.error(SQL_ERROR);
			return null;
		}

	}

	@Override
	protected String injectDeleteQuery() {
		return DELETE_ORDER;
	}

	@Override
	protected String injectUpdateQuery() {
		return UPDATE_ORDER_STATUS;
	}

	@Override
	protected String injectGetAllQuery() {
		return SELECT_ALL_FROM_ORDER;
	}

	@Override
	protected String injectGetByIdQuery() {
		return SELECT_ORDER_BY_ID;
	}

	@Override
	protected String injectInsertQuery() {
		return CREATE_ORDER;
	}

}
