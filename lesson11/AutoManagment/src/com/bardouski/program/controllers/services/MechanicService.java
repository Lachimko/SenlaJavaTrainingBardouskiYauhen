package com.bardouski.program.controllers.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

import org.apache.log4j.Logger;

import com.bardouski.config.ProgramProps;
import com.bardouski.controllers.services.IMechanicService;
import com.bardouski.db.connector.DBConnector;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.impl.Mechanic;
import com.bardouski.program.controllers.stores.dao.MechanicDAO;
import com.bardouski.program.dbprocessor.DBCSVProcessor;
import com.bardouski.propertiesholder.PropertiesContext;

public class MechanicService implements IMechanicService {

	private DBConnector dbConnector;
	private MechanicDAO mechanicDAO;
	private DBCSVProcessor dbcsvProcessor = new DBCSVProcessor(ProgramProps.valueOf("dbCSVPath"));

	public MechanicService() throws ClassNotFoundException {
		mechanicDAO = (MechanicDAO) PropertiesContext.getInstance(MechanicDAO.class);
		dbConnector = (DBConnector) PropertiesContext.getInstance(DBConnector.class);
	}

	public void add(String fullName) {
		mechanicDAO.create(dbConnector.getConnection(), new Mechanic(fullName));
	}

	@Override
	public Mechanic getMechanic(int id) {
		return mechanicDAO.getById(dbConnector.getConnection(), id);
	}

	@Override
	public void remove(int mechanicID) throws NoSuchObjectException {
		mechanicDAO.delete(dbConnector.getConnection(), mechanicID);
	}

	@Override
	public Mechanic findFreeMechanic() throws NoSuchObjectException {
		return mechanicDAO.findFreeMechanic(dbConnector.getConnection());
	}

	@Override
	public List<Mechanic> getAllMechanics() {
		return mechanicDAO.getAll(dbConnector.getConnection());
	}

	@Override
	public List<Mechanic> sortMechanicsByFullName() {
		return mechanicDAO.sortMechanicsByFullName(dbConnector.getConnection());
	}

	@Override
	public List<Mechanic> sortMechanicsByCurrentWork() {
		return mechanicDAO.sortMechanicsByWork(dbConnector.getConnection());
	}

	@Override
	public void importMechnaics() {
		List<Mechanic> imported = dbcsvProcessor.importCollection();

		Connection connection = dbConnector.getConnection();
		Savepoint savepoint = null;
		try {
			connection.setAutoCommit(false);
			savepoint = connection.setSavepoint();
			for (Mechanic mechanic : imported) {
				mechanicDAO.create(connection, mechanic);
			}
			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback(savepoint);
			} catch (SQLException e1) {
			}
		} finally {
			try {
				connection.setAutoCommit(true);
				connection.close();
			} catch (SQLException e) {
				Logger.getLogger(this.getClass().getSimpleName()).error(e.getMessage());
			}
		}
	}

	@Override
	public void exportMechanics() {
		dbcsvProcessor.exportCSV(getAllMechanics());
	}

}
