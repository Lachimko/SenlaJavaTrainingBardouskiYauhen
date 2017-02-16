package com.bardouski.controllers.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bardouski.exceptions.NoSuchObjectException;

public abstract class AbstractDAO<T> {

	protected Logger logger = Logger.getLogger("DAO Layers");
	protected static final String SQL_ERROR = "SQL Error";

	/** Inject GetById sql string with DAO query */
	protected abstract String injectGetByIdQuery();

	/** Inject Insert sql string with DAO query */
	protected abstract String injectInsertQuery();

	/** Inject Delete sql string with DAO query */
	protected abstract String injectDeleteQuery();

	/** Inject GetAll sql string with DAO query */
	protected abstract String injectGetAllQuery();

	/** Inject update sql string with DAO query */
	protected abstract String injectUpdateQuery();

	/** Fill PreparedStatement by parameters 
	 * @throws SQLException */
	protected abstract void prepareUpdateStatement(PreparedStatement statement, T object) throws SQLException;

	protected abstract void prepareInsertStatement(PreparedStatement statement, T object) throws SQLException;
	
	/** Return ready instance with completed id field */
	protected abstract T parseEntity(ResultSet resultSet);

	public T getById(Connection connection, int id) {

		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(injectGetByIdQuery());
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return parseEntity(rs);
			}
			return null;

		} catch (SQLException e) {
			logger.error(SQL_ERROR);
			return null;
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(SQL_ERROR, e);
			}
		}
	}

	/** Create DB Instance */
	public void create(Connection connection, T object) {

		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(injectInsertQuery());
			prepareInsertStatement(statement, object);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error(SQL_ERROR);
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(SQL_ERROR, e);
			}
		}
	}

	public void delete(Connection connection, int id) throws NoSuchObjectException {

		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(injectDeleteQuery());
			statement.setInt(1, id);
			if (statement.executeUpdate() == 0) {
				throw new NoSuchObjectException();
			}
		} catch (SQLException e) {
			logger.error(SQL_ERROR);
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(SQL_ERROR, e);
			}
		}
	}

	public void update(Connection connection, T object) {

		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(injectUpdateQuery());
			prepareUpdateStatement(statement, object);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error(SQL_ERROR);
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				logger.error(SQL_ERROR, e);
			}
		}
	}

	public List<T> getAll(Connection connection, String... sortingColumn) {

		List<T> tempList = new ArrayList<>();
		Statement statement = null;
		try {
			statement = connection.createStatement();
			String query = (sortingColumn.length == 1) ? injectGetAllQuery() + " order by " + sortingColumn[0]
					: injectGetAllQuery();
			ResultSet resultSet = statement.executeQuery(query);
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
				logger.error(SQL_ERROR, e);
			}
		}
	}

}
