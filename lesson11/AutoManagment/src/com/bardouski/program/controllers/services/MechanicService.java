package com.bardouski.program.controllers.services;

import java.sql.Connection;
import java.util.List;

import com.bardouski.controllers.services.IMechanicService;
import com.bardouski.controllers.stores.IMechanicStore;
import com.bardouski.db.connector.DBConnector;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.impl.Mechanic;
import com.bardouski.program.controllers.stores.MechanicStore;
import com.bardouski.program.controllers.stores.dao.MechanicDAO;
import com.bardouski.propertiesholder.PropertiesContext;

public class MechanicService implements IMechanicService{

	private Connection connection = DBConnector.getConnection();
	private MechanicDAO mechanicDAO = new MechanicDAO();
	private MechanicStore mechanicStore;

	public MechanicService() throws ClassNotFoundException{
		this.mechanicStore = (MechanicStore) PropertiesContext.getInstance(IMechanicStore.class);
	}

	public void add(String fullName) {
		mechanicDAO.create(connection, new Mechanic(fullName));
	}
	
	@Override
	public Mechanic getMechanic(int id) {
		return mechanicDAO.getById(connection, id);
	}

	@Override
	public void remove(int mechanicID) throws NoSuchObjectException {
		mechanicDAO.delete(connection, mechanicID);
	}

	@Override
	public Mechanic findFreeMechanic() throws NoSuchObjectException {
		return mechanicDAO.findFreeMechanic(connection);
	}
	
	@Override
	public List<Mechanic> getAllMechanics() {
		return mechanicDAO.getAll(connection);
	}
	
	@Override
	public List<Mechanic> sortMechanicsByFullName() {
		return mechanicDAO.sortMechanicsByFullName(connection);
	}

	@Override
	public List<Mechanic> sortMechanicsByCurrentWork() {
		return mechanicDAO.sortMechanicsByWork(connection);
	}
	
	@Override
	public void importMechnaics() {
		mechanicStore.importMechnaics();
	}

	@Override
	public void exportMechanics() {
		mechanicStore.exportMechanics();
	}

}
