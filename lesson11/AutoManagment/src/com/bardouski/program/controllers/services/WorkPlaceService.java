package com.bardouski.program.controllers.services;

import java.util.List;

import com.bardouski.controllers.services.IWorkPlaceService;
import com.bardouski.db.connector.DBConnector;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.impl.Garage;
import com.bardouski.model.impl.WorkPlace;
import com.bardouski.program.controllers.stores.dao.GarageDAO;
import com.bardouski.program.controllers.stores.dao.WorkPlaceDAO;
import com.bardouski.propertiesholder.PropertiesContext;

public class WorkPlaceService implements IWorkPlaceService {

	private WorkPlaceDAO workPlaceDAO;
	private GarageDAO garageDAO;
	private DBConnector dbConnector;

	public WorkPlaceService() throws ClassNotFoundException {
		workPlaceDAO = (WorkPlaceDAO) PropertiesContext.getInstance(WorkPlaceDAO.class);
		garageDAO = (GarageDAO) PropertiesContext.getInstance(GarageDAO.class);
		dbConnector = (DBConnector) PropertiesContext.getInstance(DBConnector.class);
	}

	@Override
	public void addGarage() {
		garageDAO.create(dbConnector.getConnection(), new Garage());
	}
	
	@Override
	public void createWorkPlaceInGarage(int garageID) {
		workPlaceDAO.create(dbConnector.getConnection(), garageID);
	}

	@Override
	public WorkPlace findFreePlace() throws NoSuchObjectException {
		return workPlaceDAO.getFreePlace(dbConnector.getConnection());
	}

	@Override
	public Garage getGarage(int id) throws NoSuchObjectException {
		return garageDAO.getById(dbConnector.getConnection(), id);
	}

	@Override
	public List<Garage> getGarages() {
		return garageDAO.getAll(dbConnector.getConnection());
	}

	@Override
	public boolean removeWorkPlaceInGarage(int garageID, int placeID) throws NoSuchObjectException {
		return workPlaceDAO.removeWorkPlaceInGarage(dbConnector.getConnection(), garageID, placeID);
	}

}
