package com.bardouski.program.controllers.services;

import java.util.List;

import com.bardouski.controllers.services.IWorkPlaceService;
import com.bardouski.controllers.stores.IWorkPlaceStore;
import com.bardouski.exceptions.EmptyCollectionException;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IGarage;
import com.bardouski.model.impl.Garage;
import com.bardouski.model.impl.WorkPlace;
import com.bardouski.program.controllers.stores.WorkPlaceStore;
import com.bardouski.propertiesholder.PropertiesContext;

public class WorkPlaceService implements IWorkPlaceService {

	private WorkPlaceStore workPlaceStore;

	public WorkPlaceService() throws ClassNotFoundException {
		workPlaceStore = (WorkPlaceStore) PropertiesContext.getInstance(IWorkPlaceStore.class);
	}

	@Override
	public void addWorkPlaceInGarage(IGarage garage) {
		workPlaceStore.addWorkPlace(garage);
	}

	@Override
	public void addGarage(IGarage garage) {
		workPlaceStore.addGarage(garage);
	}

	@Override
	public boolean removeWorkPlaceInGarage(IGarage garage, int placeId) {
		return workPlaceStore.removeWorkPlaceInGarage(garage, placeId);
	}

	@Override
	public WorkPlace findFreePlace() throws EmptyCollectionException {
		return workPlaceStore.getFreePlace();
	}

	@Override
	public Garage getGarage(int id) throws NoSuchObjectException {
		return workPlaceStore.getGarage(id);
	}

	@Override
	public List<Garage> getGarages() {
		return workPlaceStore.getGarages();
	}

}
