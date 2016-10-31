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

	private static final String NOTHING_TO_REMOVE = "Nothing to remove";

	private List<Garage> garages;

	public WorkPlaceStore(DBProcessor dbProcessor) {

		if (Store.dbProcessor == null) {
			Store.dbProcessor = dbProcessor;
		}

		garages = Store.dbProcessor.getGaragesDB();
		setGaragesLink(garages);
	}

	public void addWorkPlace(Garage garage) {
		garage.getWorkPlaces().add(new WorkPlace());
	}

	public void addGarage(Garage garage) {
		garages.add(garage);
	}

	/**
	 * Find workplace by ID in garage. Throws exc if collection is empty; return
	 * false if garage or workplace had not been found
	 */
	public boolean removeWorkPlaceInGarage(int placeId, int garageId) throws EmptyCollectionException {

		boolean result = false;

		if (!garages.isEmpty()) {

			OUTER: for (Garage garage : garages) {

				if (garage.getId() == garageId) {

					Iterator<WorkPlace> it = garage.getWorkPlaces().iterator();
					while (it.hasNext()) {

						if (it.next().getId() == placeId) {
							it.remove();
							result = true;
							break OUTER;
						}
					}
				}
			}

		} else {
			throw new EmptyCollectionException();
		}

		return result;
	}

	public WorkPlace findFreePlace() {

		for (Garage garage : garages) {
			for (WorkPlace workPlace : garage.getWorkPlaces()) {
				return workPlace;
			}
		}
		return null;

	}

	/* GETTERS/SETTERS */
	public List<Garage> getGarages() {
		return garages;
	}

	public void setGarages(List<Garage> garages) {
		this.garages = garages;
	}

	public Garage getGarage(int id) throws NoSuchObjectException{
		
		for (Garage garage : garages) {
			if (garage.getId() == id) {
				return garage;
			}
		}
		
		throw new NoSuchObjectException();
	}
}
