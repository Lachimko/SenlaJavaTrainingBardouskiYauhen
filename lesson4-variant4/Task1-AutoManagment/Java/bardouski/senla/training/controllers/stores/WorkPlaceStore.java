package bardouski.senla.training.controllers.stores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import bardouski.senla.training.model.Garage;
import bardouski.senla.training.model.WorkPlace;

public class WorkPlaceStore {

	private static final String NOTHING_TO_REMOVE = "Nothing to remove";
	private static final String CREATE_GARAGE = "First of all, create Garage";
	private static List<Garage> garages = new ArrayList<Garage>();

	/**
	 * Convert to String the new instance of Garage constructed with String
	 * garageIdentifier parameter; write converted instance to remote DB(add);
	 * Than we must return refreshed collection to WorkPlaceManager's ArrayList
	 */

	public void addWorkPlace(int garageId) {

		if (!garages.isEmpty()) {

			for (Garage garage : garages) {
				if (garage.getId() == garageId) {

					garage.getWorkPlaces().add(new WorkPlace());
				}
			}
		} else {
			System.out.println(CREATE_GARAGE);
			return;
		}

	}

	public void addGarage() {

		garages.add(new Garage());
	}

	public void removeWorkPlaceInGarage(int placeId, int garageId) {

		if (!garages.isEmpty()) {

			for (Garage garage : garages) {

				if (garage.getId() == garageId) {

					Iterator<WorkPlace> it = garage.getWorkPlaces().iterator();
					while (it.hasNext()) {

						if (it.next().getId() == placeId) {
							it.remove();

						}
					}
				}
			}

		} else {
			System.out.println(NOTHING_TO_REMOVE);
		}
	}

	public static WorkPlace findFreePlace() {

		for (Garage garage : garages) {
			for (WorkPlace workPlace : garage.getWorkPlaces()) {
				return workPlace;
			}
		}
		return null;

	}

	/* GETTERS/SETTERS */
	public static List<Garage> getGarages() {
		return garages;
	}

	public static void setGarages(List<Garage> garages) {
		WorkPlaceStore.garages = garages;
	}

}
