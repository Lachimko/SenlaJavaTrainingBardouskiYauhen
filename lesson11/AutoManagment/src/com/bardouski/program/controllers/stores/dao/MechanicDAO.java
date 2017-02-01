package com.bardouski.program.controllers.stores.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.impl.Mechanic;

public class MechanicDAO extends AbstractDAO<Mechanic> {

	private static final String UPDATE_MECHANIC = "update mechanic set id_order=? where id_mechanic=?";

	private Logger logger = Logger.getLogger(MechanicDAO.class.getSimpleName());

	private static final String SELECT_ALL_FROM_MECHANIC = "select * from mechanic";
	private static final String GET_FREE_MECHANIC = "select * from mechanic where id_order is null";
	private static final String DELETE_MECHANIC_BY_ID = "delete from mechanic where id_mechanic = ?";
	private static final String SELECT_MECHANIC_BY_ID = "select * from mechanic where id_mechanic = ?";
	private static final String INSERT_MECHANIC = "insert into mechanic(full_name) value (?)";

	@Override
	public String injectInsertQuery() {
		return INSERT_MECHANIC;
	}

	@Override
	public String injectGetByIdQuery() {
		return SELECT_MECHANIC_BY_ID;
	}

	@Override
	public String injectDeleteQuery() {
		return DELETE_MECHANIC_BY_ID;
	}

	@Override
	public String injectGetAllQuery() {
		return SELECT_ALL_FROM_MECHANIC;
	}

	@Override
	public String injectUpdateQuery() {
		return UPDATE_MECHANIC;
	}

	@Override
	public Mechanic parseEntity(ResultSet resultSet) {
		try {
			return new Mechanic(resultSet.getInt("id_mechanic"), resultSet.getString("full_name"), null);
		} catch (SQLException e) {
			return null;
		}
	}

	public Mechanic findFreeMechanic(Connection connection) throws NoSuchObjectException {

		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(GET_FREE_MECHANIC);
			if (resultSet.next()) {
				return parseEntity(resultSet);
			} else {
				throw new NoSuchObjectException();
			}
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

	public List<Mechanic> sortMechanicsByFullName(Connection connection) {
		return getAll(connection, "full_name");
	}

	public List<Mechanic> sortMechanicsByWork(Connection connection) {
		return getAll(connection, "id_order");
	}

	@Override
	public void prepareUpdateStatement(PreparedStatement statement, Mechanic object) throws SQLException {
		statement.setInt(1, object.getCurrentOrder().getId());
		statement.setInt(2, object.getId());
	}

	@Override
	public void prepareInsertStatement(PreparedStatement statement, Mechanic object) throws SQLException {
		statement.setString(1, object.getFullName());
	}

}
