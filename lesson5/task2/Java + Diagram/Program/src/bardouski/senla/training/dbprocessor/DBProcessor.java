package bardouski.senla.training.dbprocessor;

import java.util.ArrayList;
import java.util.List;

import com.danco.training.TextFileWorker;

import bardouski.senla.training.converter.Converter;
import bardouski.senla.training.model.Garage;
import bardouski.senla.training.model.Mechanic;
import bardouski.senla.training.model.Order;

public class DBProcessor {

	private static final String FILESYSTEM_PATH = "src\\resources\\db\\";
	private static final String DB_DEFAULT_FILENAME = "db.txt";
	private String dbPath;

	private List<Mechanic> mechanicsDB;
	private List<Order> ordersDB;
	private List<Garage> garagesDB;
	
	private Converter converter;

	private TextFileWorker fileWorker;

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
		converter = new Converter();
		initDB();
	}

	// DB Write
	public void writeToDB(List<Order> orders, List<Garage> garages, List<Mechanic> mechanics) {

		String[] stringOrders = converter.getOrderConverter().serialize(orders);
		String[] stringMechanics = converter.getMechanicConverter().serialize(mechanics);

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
		List<Garage> garages = new ArrayList<>();

		// first of all find all mechanics, deserialize them and add to temp
		// list
		for (String line : list) {

			if (line.startsWith(patternMechanic)) {
				mechanics.add(converter.getMechanicConverter().deserialize(line));
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
				Order current = converter.getOrderConverter().deserialize(line);

				// find Mechanic Object from temp list
				for (Mechanic mechanic : mechanics) {
					if (mechanic.getId() == mechanicId) {

						// link objects between themselves
						current.setMechanic(mechanic);
						mechanic.setCurrentOrder(current);
						break;
					}
				}

				// add to temp list
				orders.add(current);
			}
		}

		// write to store collections
		ordersDB = (orders.isEmpty()) ? new ArrayList<>() : orders;
		mechanicsDB = (mechanics.isEmpty()) ? new ArrayList<>() : mechanics;
		garagesDB = (garages.isEmpty()) ? new ArrayList<>() : garages; 

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

	public List<Mechanic> getMechanicsDB() {
		return mechanicsDB;
	}

	public List<Order> getOrdersDB() {
		return ordersDB;
	}

	public List<Garage> getGaragesDB() {
		return garagesDB;
	}
	
}
