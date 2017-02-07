package com.bardouski.program.controllers.stores.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.bardouski.controllers.dao.AbstractDAO;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.impl.WorkPlace;

public class WorkPlaceDAO extends AbstractDAO<WorkPlace> {

	private static final String FREE_PLACE_PATTERN = "currentOrder";
	private static final String REMOVE_WORKPLACE_IN_GARAGE = "delete from workplace where id_garage = ? and id_workplace = ?";
	private static final String INSERT_WORKPLACE_IN_GARAGE = "insert into workplace(id_garage) value (?)";

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

	public WorkPlace getFreePlace(Session session) throws NoSuchObjectException {

		Criteria criteria = session.createCriteria(returnClass()).add(Restrictions.isNull(FREE_PLACE_PATTERN));
		return (WorkPlace) criteria.setMaxResults(1).list();
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

	@Deprecated
	public void create(Connection connection, WorkPlace object) {
		return; // Overloading method from abstract class. use create(Connection
				// connection, int garageID)
	}

	@Override
	protected Class<WorkPlace> returnClass() {
		return WorkPlace.class;
	}
}
