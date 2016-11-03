package bardouski.senla.training.controllers.stores;

import java.util.Iterator;
import java.util.List;

import bardouski.senla.training.controllers.stores.storeparent.Store;
import bardouski.senla.training.dbprocessor.DBProcessor;
import bardouski.senla.training.exceptions.EmptyCollectionException;
import bardouski.senla.training.exceptions.NoSuchObjectException;
import bardouski.senla.training.model.Garage;
import bardouski.senla.training.model.WorkPlace;

public class WorkPlaceStore extends Store {

	private List<Garage> garages;

	// Constructors, Getters/Setters
	public WorkPlaceStore(DBProcessor dbProcessor) {

		if (Store.dbProcessor == null) {
			Store.dbProcessor = dbProcessor;
		}

		garages = Store.dbProcessor.getGaragesDB();
		setGaragesLink(garages);
	}

	public List<Garage> getGarages() {
		return garages;
	}

	public void setGarages(List<Garage> garages) {
		this.garages = garages;
	}
	// END Constructors, Getters/Setters

	public void addWorkPlace(Garage garage) {
		garage.getWorkPlaces().add(new WorkPlace());
	}

	public void addGarage(Garage garage) {
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

	/** Return first workplace with null Order, if no such object return null 
	 * @throws EmptyCollectionException */
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

	/**return garage by id. Exception if no such garage*/
	public Garage getGarage(int id) throws NoSuchObjectException {

		for (Garage garage : garages) {
			if (garage.getId() == id) {
				return garage;
			}
		}
		throw new NoSuchObjectException();
	}

	
}
