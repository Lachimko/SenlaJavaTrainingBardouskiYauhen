package com.bardouski.program.controllers.stores.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.impl.WorkPlace;

public class WorkPlaceDAO extends AbstractDAO<WorkPlace> {

	private static final String UPDATE_WORKPLACE = "update workplace set id_order=? where id_workplace=?";
	private static final String SELECT_WORKPLACE_BY_ID = "select * from workplace where id_workplace = ?";
	private static final String SELECT_ALL_FROM_WORKPLACE = "select * from workplace";
	private static final String REMOVE_WORKPLACE_IN_GARAGE = "delete from workplace where id_garage = ? and id_workplace = ?";
	private static final String FREE_WORKPLACE = "select * from workplace where id_order is null limit 1";
	private static final String INSERT_WORKPLACE_IN_GARAGE = "insert into workplace(id_garage) value (?)";
	private Logger logger = Logger.getLogger(WorkPlaceDAO.class.getSimpleName());

	/** Unique method, field garage_id in table workplace cannot be null. */
	public void create(Connection connection, int garageID) {

		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(INSERT_WORKPLACE_IN_GARAGE);
			statement.setInt(1, garageID);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error(SQL_ERROR);
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}

	}

	public WorkPlace getFreePlace(Connection connection) throws NoSuchObjectException {

		Statement statement = null;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(FREE_WORKPLACE);
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

	public boolean removeWorkPlaceInGarage(Connection connection, int garageID, int placeID)
			throws NoSuchObjectException {

		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(REMOVE_WORKPLACE_IN_GARAGE);
			statement.setInt(1, garageID);
			statement.setInt(2, placeID);
			if (statement.executeUpdate() == 0) {
				throw new NoSuchObjectException();
			} else {
				return true;
			}
		} catch (SQLException e) {
			logger.error(SQL_ERROR);
			return false;
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	@Override
	public String injectGetByIdQuery() {
		return SELECT_WORKPLACE_BY_ID;
	}

	@Override
	public String injectUpdateQuery() {
		return UPDATE_WORKPLACE;
	}

	@Override
	public String injectGetAllQuery() {
		return SELECT_ALL_FROM_WORKPLACE;
	}

	@Override
	public WorkPlace parseEntity(ResultSet resultSet) {

		try {
			WorkPlace temp = new WorkPlace();
			temp.setId(resultSet.getInt("id_workplace"));
			return temp;

		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public String injectInsertQuery() {
		return null;
	}

	@Override
	public String injectDeleteQuery() {
		return null;
	}

	@Override
	public void prepareUpdateStatement(PreparedStatement statement, WorkPlace object) throws SQLException {
		statement.setInt(1, object.getOrder().getId());
		statement.setInt(2, object.getId());
	}

	@Override
	public void prepareInsertStatement(PreparedStatement statement, WorkPlace object) throws SQLException {
		// error method
		statement.setInt(1, object.getId());
		
	}

}
