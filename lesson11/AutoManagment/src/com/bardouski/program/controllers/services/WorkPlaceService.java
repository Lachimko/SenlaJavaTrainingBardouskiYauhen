package com.bardouski.program.controllers.services;

import java.sql.Connection;
import java.util.List;

import com.bardouski.controllers.services.IWorkPlaceService;
import com.bardouski.db.connector.DBConnector;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.impl.Garage;
import com.bardouski.model.impl.WorkPlace;
import com.bardouski.program.controllers.stores.dao.GarageDAO;
import com.bardouski.program.controllers.stores.dao.WorkPlaceDAO;

public class WorkPlaceService implements IWorkPlaceService {

	private WorkPlaceDAO workPlaceDAO = new WorkPlaceDAO();
	private GarageDAO garageDAO = new GarageDAO();
	private Connection connection = DBConnector.getConnection();

	public WorkPlaceService() throws ClassNotFoundException {
	}

	@Override
	public void addGarage() {
		garageDAO.create(connection, new Garage());
	}
	
	@Override
	public void createWorkPlaceInGarage(int garageID) {
		workPlaceDAO.create(connection, garageID);
	}

	@Override
	public WorkPlace findFreePlace() throws NoSuchObjectException {
		return workPlaceDAO.getFreePlace(connection);
	}

	@Override
	public Garage getGarage(int id) throws NoSuchObjectException {
		return garageDAO.getById(connection, id);
	}

	@Override
	public List<Garage> getGarages() {
		return garageDAO.getAll(connection);
	}

	@Override
	public boolean removeWorkPlaceInGarage(int garageID, int placeID) throws NoSuchObjectException {
		return workPlaceDAO.removeWorkPlaceInGarage(connection, garageID, placeID);
	}

}
