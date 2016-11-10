package com.bardouski.program.controllers.stores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.bardouski.program.comparators.GarageIdComparator;
import com.bardouski.program.comparators.WorkPlaceIdComparator;
import com.bardouski.program.dbprocessor.DBProcessor;
import com.bardouski.program.exceptions.EmptyCollectionException;
import com.bardouski.program.exceptions.NoSuchObjectException;
import com.bardouski.program.model.Garage;
import com.bardouski.program.model.WorkPlace;

public class WorkPlaceStore extends Store {

	private int workPlaceNextId;
	private int garageNextId;

	private List<Garage> garages;

	/**
	 * Initialize dbProcessor, write garages by returned collection from DB,
	 * calculate highest id from collections
	 */
	public WorkPlaceStore(DBProcessor dbProcessor) {

		if (dbProcessor == null) {
			this.dbProcessor = dbProcessor;
		}

		garages = dbProcessor.getGaragesDB();

		if (!garages.isEmpty()) {
			List<Garage> sortedList = new ArrayList<>(garages);
			Collections.sort(sortedList, new GarageIdComparator());
			garageNextId = (sortedList.get(0).getId()) + 1;

			List<WorkPlace> placesList = new ArrayList<>();
			int startIndex = 0;
			for (Garage garage : sortedList) {
				placesList.addAll(startIndex, garage.getWorkPlaces());
				startIndex += garage.getWorkPlaces().size();
			}
			Collections.sort(placesList, new WorkPlaceIdComparator());
			workPlaceNextId = (sortedList.get(0).getId()) + 1;
		}
	}

	public List<Garage> getGarages() {
		return garages;
	}
	// END Constructors, Getters/Setters

	public void addWorkPlace(Garage garage) {

		WorkPlace temp = new WorkPlace();
		temp.setId(workPlaceNextId);
		workPlaceNextId++;

		garage.getWorkPlaces().add(temp);
	}

	public void addGarage(Garage garage) {
		garage.setId(garageNextId);
		garageNextId++;
		garages.add(garage);
	}

	/**
	 * Find workplace by ID in garage.return false if workplace not found
	 */
	public boolean removeWorkPlaceInGarage(Garage garage, int placeId) {

		Iterator<WorkPlace> it = garage.getWorkPlaces().iterator();
		while (it.hasNext()) {

			if (it.next().getId() == placeId) {
				it.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 * Return first workplace with null Order, if no such object return null
	 * 
	 * @throws EmptyCollectionException
	 */
	public WorkPlace getFreePlace() throws EmptyCollectionException {

		if (!garages.isEmpty()) {

			for (Garage garage : garages) {
				if (garage.getWorkPlaces() != null) {

					for (WorkPlace workPlace : garage.getWorkPlaces()) {
						if (workPlace.getOrder() == null) {
							return workPlace;
						}
					}
				}
			}
		}
		throw new EmptyCollectionException();
	}

	/** return garage by id. Exception if no such garage */
	public Garage getGarage(int id) throws NoSuchObjectException {

		for (Garage garage : garages) {
			if (garage.getId() == id) {
				return garage;
			}
		}
		throw new NoSuchObjectException();
	}

}
