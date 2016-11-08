package bardouski.senla.training.dbprocessor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bardouski.senla.training.comparators.GarageIdComparator;
import bardouski.senla.training.comparators.MechanicIdComparator;
import bardouski.senla.training.comparators.OrderIdComparator;
import bardouski.senla.training.comparators.WorkPlaceIdComparator;
import bardouski.senla.training.model.Garage;
import bardouski.senla.training.model.Mechanic;
import bardouski.senla.training.model.Order;
import bardouski.senla.training.model.WorkPlace;
import bardouski.senla.training.serializator.Serializator;

public class DBProcessor {

	private String dbPath;

	private List<Mechanic> mechanicsDB;
	private List<Order> ordersDB;
	private List<Garage> garagesDB;

	private Serializator serializator;

	// Constructors, Getters/Setters
	/**
	 * Deserialize all collections from DB and save them to local lists. That
	 * lists will shared with all stores
	 * 
	 * @param fileName
	 *            - path to DB
	 * @throws FileNotFoundException
	 *             - if no file was founded
	 */
	public DBProcessor(String fileName) throws FileNotFoundException {

		dbPath = fileName;

		serializator = new Serializator(dbPath);
		deserializeAndFillTempLists();
	}

	public List<Mechanic> getMechanicsDB() {
		return (mechanicsDB == null) ? new ArrayList<>() : mechanicsDB;
	}

	public List<Order> getOrdersDB() {
		return (ordersDB == null) ? new ArrayList<>() : ordersDB;
	}

	public List<Garage> getGaragesDB() {
		return (garagesDB == null) ? new ArrayList<>() : garagesDB;
	}
	// END Constructors, Getters/Setters

	/** Write to DB data */
	@SuppressWarnings("rawtypes")
	public void writeToDB(List<List> lists) {
		serializator.writeToDB(lists);
	}

	/**
	 * Take lists from serializer (serializer returns List<Object>) and set
	 * correct type of collection by checking type of the first element.
	 * Generate empty lists if returned collection is null. Synchronize model id
	 * counters.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void deserializeAndFillTempLists() {

		List<List> lists = serializator.getLists();

		if (lists != null) {
			for (List list : lists) {
				if (!list.isEmpty()) {

					if (list.get(0) instanceof Mechanic) {
						mechanicsDB = list;
						continue;
					}
					if (list.get(0) instanceof Garage) {
						garagesDB = list;
						continue;
					}
					if (list.get(0) instanceof Order) {
						ordersDB = list;
					}
				}
			}
		}
		// if returned collection is null - push new arraylist
		if (mechanicsDB == null) {
			mechanicsDB = new ArrayList<>();
		}
		if (garagesDB == null) {
			garagesDB = new ArrayList<>();
		}
		if (ordersDB == null) {
			ordersDB = new ArrayList<>();
		}
		syncronizeIdCounters();
	}

	/**
	 * Sort copy of collection by id, get the highest id and set it's value to
	 * idCount of a Class
	 */
	private void syncronizeIdCounters() {

		if (!mechanicsDB.isEmpty()) {
			List<Mechanic> sortedList = new ArrayList<>(mechanicsDB);
			Collections.sort(sortedList, new MechanicIdComparator());
			Mechanic.setIdCount((sortedList.get(sortedList.size() - 1).getId()) + 1);
		}
		if (!garagesDB.isEmpty()) {
			List<Garage> sortedList = new ArrayList<>(garagesDB);
			Collections.sort(sortedList, new GarageIdComparator());
			Garage.setIdCount((sortedList.get(sortedList.size() - 1).getId()) + 1);

			List<WorkPlace> placesList = new ArrayList<>();
			int startIndex = 0;
			for (Garage garage : sortedList) {
				placesList.addAll(startIndex, garage.getWorkPlaces());
				startIndex += garage.getWorkPlaces().size();
			}
			Collections.sort(placesList, new WorkPlaceIdComparator());
			WorkPlace.setIdCount((placesList.get(placesList.size() - 1).getId()) + 1);
		}
		if (!ordersDB.isEmpty()) {
			List<Order> sortedList = new ArrayList<>(ordersDB);
			Collections.sort(sortedList, new OrderIdComparator());
			Order.setIdCount(sortedList.get(sortedList.size() - 1).getId() + 1);
		}
	}

}
