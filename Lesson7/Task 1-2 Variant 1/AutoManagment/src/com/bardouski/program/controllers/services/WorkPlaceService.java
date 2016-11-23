package com.bardouski.program.controllers.services;

import java.util.List;

import com.bardouski.program.controllers.stores.IWorkPlaceStore;
import com.bardouski.program.controllers.stores.WorkPlaceStore;
import com.bardouski.program.exceptions.EmptyCollectionException;
import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.model.Garage;
import com.bardouski.program.model.WorkPlace;
import com.bardouski.propertiesholder.PropertiesContext;

public class WorkPlaceService implements IWorkPlaceService {

	private IWorkPlaceStore workPlaceStore;

	// Constructors, Getters/Setters

	public WorkPlaceService() throws ClassNotFoundException {
		workPlaceStore = (WorkPlaceStore) PropertiesContext.getInstance(IWorkPlaceStore.class);
	}
	// END Constructors, Getters/Setters

	public void addWorkPlaceInGarage(Garage garage) {
		workPlaceStore.addWorkPlace(garage);
	}

	public void addGarage(Garage garage) {
		workPlaceStore.addGarage(garage);
	}

	public boolean removeWorkPlaceInGarage(Garage garage, int placeId) {
		return workPlaceStore.removeWorkPlaceInGarage(garage, placeId);
	}

	public WorkPlace findFreePlace() throws EmptyCollectionException {
		return workPlaceStore.getFreePlace();
	}

	public Garage getGarage(int id) throws NoSuchObjectException {
		return workPlaceStore.getGarage(id);
	}

	public List<Garage> getGarages() {
		return workPlaceStore.getGarages();
	}
}
