package bardouski.senla.training.dbprocessor;

import java.util.ArrayList;
import java.util.List;

import com.danco.training.TextFileWorker;

import bardouski.senla.training.controllers.stores.MechanicStore;
import bardouski.senla.training.controllers.stores.OrderStore;
import bardouski.senla.training.converter.Converter;
import bardouski.senla.training.model.Mechanic;
import bardouski.senla.training.model.Order;

public class DBProcessor {

	private static final String FILESYSTEM_PATH = System.getProperty("user.dir") + "/";
	private static final String DB_DEFAULT_FILENAME = "db.txt";
	private String dbPath;

	private List<Mechanic> mechanicsLink;
	private List<Order> ordersLink;

	private Converter converter;
	private TextFileWorker fileWorker = null;

	/**
	 * Initialize by setting fileName of DB textfile into class dbPath, than
	 * create new TextFileWorker with Path and makes references to collections
	 * from Stores
	 */
	public DBProcessor(String fileName) {

		if (fileName.equals("")) {
			dbPath = FILESYSTEM_PATH + DB_DEFAULT_FILENAME;
		} else {
			dbPath = FILESYSTEM_PATH + fileName;
		}

		fileWorker = new TextFileWorker(dbPath);

		mechanicsLink = MechanicStore.getMechanics();
		ordersLink = OrderStore.getOrders();
	}

	// DB Write
	public void writeToDB(List<Order> orders, List<Mechanic> mechanics) {

		String[] stringOrders = Converter.orderConverter.serialize(ordersLink);
		String[] stringMechanics = Converter.mechanicConverter.serialize(mechanicsLink);

		String[] result = new String[stringOrders.length + stringMechanics.length];

		System.arraycopy(stringOrders, 0, result, 0, stringOrders.length);
		System.arraycopy(stringMechanics, 0, result, stringOrders.length, stringMechanics.length);

		fileWorker.writeToFile(result);
	}

	// DB Initialize
	public void initDB() {

		// patterns to find matches in strings
		String patternMechanic = "Mechanic/";
		String patternOrder = "Order/";
		String patternId = "id=";

		// return db as String list
		List<String> list = returnDBasList();

		// Temps for deserialized objects
		List<Mechanic> mechanics = new ArrayList<>();
		List<Order> orders = new ArrayList<>();

		// first of all find all mechanics, deserialize them and add to temp
		// list
		for (String line : list) {

			if (line.startsWith(patternMechanic)) {
				mechanics.add(Converter.mechanicConverter.deserialize(line));
			}
		}

		// than find all orders
		for (String line : list) {

			if (line.startsWith(patternOrder)) {

				// parse mechanic ID value from current string
				int startFieldPosition = line.indexOf(patternMechanic) + patternMechanic.length();

				startFieldPosition = line.indexOf(patternId) + patternId.length();
				int endFieldPosition = line.indexOf(";", startFieldPosition);

				// mechanicId founded
				int mechanicId = Integer.parseInt((line.substring(startFieldPosition, endFieldPosition)));

				// create Order object
				Order current = Converter.orderConverter.deserialize(line);

				// find Mechanic Object from temp list
				for (Mechanic mechanic : mechanics) {
					if (mechanic.getId() == mechanicId) {

						// link objects between themselves
						current.setMechanic(mechanic);
						mechanic.setCurrentOrder(current);
					}
				}

				// add to temp list
				orders.add(current);
			}
		}

		// write to store collections and increment id Counter
		OrderStore.setOrders(orders);
		Order.setIdCount(orders.size());

		MechanicStore.setMechanics(mechanics);
		Mechanic.setIdCount(mechanics.size());
	}

	private String[] readDB() {

		return fileWorker.readFromFile();
	}

	private List<String> returnDBasList() {

		String[] db = readDB();
		List<String> list = new ArrayList<>();

		for (int i = 0; i < db.length; i++) {
			list.add(db[i]);
		}

		return list;
	}

}
