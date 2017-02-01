package com.bardouski.program.controllers.stores;

import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.bardouski.controllers.stores.IWorkPlaceStore;
import com.bardouski.exceptions.EmptyCollectionException;
import com.bardouski.exceptions.NoDBConnectionException;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IGarage;
import com.bardouski.model.impl.Garage;
import com.bardouski.model.impl.WorkPlace;
import com.bardouski.program.comparators.GarageIdComparator;
import com.bardouski.program.comparators.WorkPlaceIdComparator;

/** Deprecated class. Use WorkPlaceDAO*/
@Deprecated
public class WorkPlaceStore extends Store implements IWorkPlaceStore {

	private volatile Integer workPlaceNextId;
	private volatile Integer garageNextId;

	private List<Garage> garages;

	/**
	 * Initialize dbProcessor, write garages by returned collection from DB,
	 * calculate highest id from collections
	 */
	public WorkPlaceStore() throws NoDBConnectionException, FileNotFoundException, ClassNotFoundException {

		if (Store.dbProcessor != null) {
			garages = dbProcessor.getGaragesDB();
			garageNextId = getIdCounterOfGarageOfWorkPlaceStore();
			workPlaceNextId = getIdCounterOfWorkPlaceOfWorkPlaceStore();
		}
	}

	/**
	 * Return correct id after processing list. Not Synchronized method, cause
	 * call itself during initializn' store
	 */
	@SuppressWarnings("unchecked")
	private int getIdCounterOfWorkPlaceOfWorkPlaceStore() {

		if (!garages.isEmpty()) {

			List<Garage> sortedList = new ArrayList<>(garages);

			List<WorkPlace> placesList = new ArrayList<>();
			int startIndex = 0;
			for (IGarage garage : sortedList) {
				placesList.addAll(startIndex, (Collection<? extends WorkPlace>) garage.getWorkPlaces());
				startIndex += garage.getWorkPlaces().size();
			}
			Collections.sort(placesList, new WorkPlaceIdComparator());
			return (sortedList.get(0).getId()) + 1;

		} else

		{
			return 1;
		}
	}

	/** Return correct id after processing list. Not Synchronized method, cause
	 * call itself during initializn' store */
	private int getIdCounterOfGarageOfWorkPlaceStore() {

		if (!garages.isEmpty()) {
			List<Garage> sortedList = new ArrayList<>(garages);
			Collections.sort(sortedList, new GarageIdComparator());
			return (sortedList.get(0).getId()) + 1;

		} else {
			return 1;
		}
	}

	@Override
	public List<Garage> getGarages() {
		synchronized (garages) {
			return garages;
		}
	}

	@Override
	public void addWorkPlace(IGarage garage) {

		WorkPlace temp = new WorkPlace();
		synchronized (workPlaceNextId) {
			
			temp.setId(workPlaceNextId);
			workPlaceNextId++;
		}
		
		synchronized (garages) {
			
			for (Garage collectionGarage : garages) {
				if (collectionGarage.getId() == collectionGarage.getId()) {
					collectionGarage.getWorkPlaces().add(new WorkPlace());
				}
			}
		}
	}

	@Override
	public void addGarage(IGarage garage) {
		synchronized (garageNextId) {
			garage.setId(garageNextId);
			garageNextId++;
		}
		synchronized (garages) {
			garages.add((Garage) garage);
		}
	}

	/**
	 * Find workplace by ID in garage.return false if workplace not found
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean removeWorkPlaceInGarage(IGarage garage, int placeId) {

		Iterator<WorkPlace> it = (Iterator<WorkPlace>) garage.getWorkPlaces().iterator();
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
	@Override
	public WorkPlace getFreePlace() {

		if (!garages.isEmpty()) {

			synchronized (garages) {
				
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
			
		}
		return null;
	}

	/** return garage by id. Exception if no such garage */
	@Override
	public Garage getGarage(int id) throws NoSuchObjectException {

		synchronized (garages) {

			for (Garage garage : garages) {
				if (garage.getId() == id) {
					return garage;
				}
			}
		}
		throw new NoSuchObjectException();
	}

}
