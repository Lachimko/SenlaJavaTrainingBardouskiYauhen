package com.bardouski.program.controllers.stores.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bardouski.controllers.dao.AbstractDAO;
import com.bardouski.controllers.dao.ITaskDAO;
import com.bardouski.model.impl.Task;
import com.bardouski.mysqlparser.MySQLDateParser;

public class TaskDAO extends AbstractDAO<Task> implements ITaskDAO {

	private static final String UPDATE_TASK = "update task set to_do=?, request_date=?, start_date=?, complete_date=?, price=? where id_task=?";
	private static final String INSERT_INTO_TASK = "insert into task(to_do, request_date, start_date, complete_date, price) value (?, now(), ?, ?, ?)";
	private static final String SELECT_FROM_TASK_BY_ID = "select * from task where id_task = ?";
	private static final String DELETE_TASK_BY_ID = "delete from task where id_task = ?";
	private static final String SELECT_ALL_FROM_TASK = "select * from task";
	
	@Override
	protected String injectGetByIdQuery() {
		return SELECT_FROM_TASK_BY_ID;
	}

	@Override
	protected String injectInsertQuery() {
		return INSERT_INTO_TASK;
	}

	@Override
	protected String injectDeleteQuery() {
		return DELETE_TASK_BY_ID;
	}

	@Override
	protected String injectGetAllQuery() {
		return SELECT_ALL_FROM_TASK;
	}

	@Override
	protected Task parseEntity(ResultSet resultSet) {

		try {
			return new Task(resultSet.getInt("id_task"), resultSet.getString("to_do"), resultSet.getDate("request_date"),
					resultSet.getDate("start_date"), resultSet.getDate("complete_date"), resultSet.getDouble("price"));
		} catch (SQLException e) {
			logger.error(SQL_ERROR);
			return null;
		}
	}

	@Override
	protected String injectUpdateQuery() {
		return UPDATE_TASK;
	}

	@Override
	protected void prepareUpdateStatement(PreparedStatement statement, Task object) throws SQLException {
		statement.setString(1, object.getToDo());
		statement.setString(2, MySQLDateParser.generateStringFromDate(object.getRequestDate()));
		statement.setString(3, MySQLDateParser.generateStringFromDate(object.getStartDate()));
		statement.setString(4, MySQLDateParser.generateStringFromDate(object.getCompleteDate()));
		statement.setDouble(5, object.getPrice());
		statement.setInt(6, object.getId());
	}

	@Override
	protected void prepareInsertStatement(PreparedStatement statement, Task object) throws SQLException {
		statement.setString(1, object.getToDo());
		statement.setString(2, MySQLDateParser.generateStringFromDate(object.getStartDate()));
		statement.setString(3, MySQLDateParser.generateStringFromDate(object.getCompleteDate()));
		statement.setDouble(4, object.getPrice());
	}

}
