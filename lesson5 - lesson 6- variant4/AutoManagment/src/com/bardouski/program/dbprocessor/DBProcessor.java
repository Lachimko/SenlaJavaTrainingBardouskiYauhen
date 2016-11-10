package com.bardouski.program.dbprocessor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.bardouski.program.model.Garage;
import com.bardouski.program.model.Mechanic;
import com.bardouski.program.model.Order;
import com.bardouski.program.serializator.FacadeResultContainer;
import com.bardouski.program.serializator.Serializator;

/**
 * Deserialize all collections from DB and save them to local lists. That lists
 * will shared with all stores
 */
public class DBProcessor {

	private String dbPath;

	private FacadeResultContainer resultContainer;
	private Serializator serializator;

	// Constructors, Getters/Setters
	public DBProcessor(String fileName) throws FileNotFoundException {

		dbPath = fileName;

		serializator = new Serializator(dbPath);
		getListsFromDB();
	}

	public List<Mechanic> getMechanicsDB() {
		return (resultContainer.getResultMechanics() == null) ? new ArrayList<>() : resultContainer.getResultMechanics();
	}

	public List<Order> getOrdersDB() {
		return (resultContainer.getResultOrders() == null) ? new ArrayList<>() : resultContainer.getResultOrders();
	}

	public List<Garage> getGaragesDB() {
		return (resultContainer.getResultGarages() == null) ? new ArrayList<>() : resultContainer.getResultGarages();
	}
	// END Constructors, Getters/Setters

	/** Push to FacadeResultContainer collections and throw to sateToFile */
	public void saveToFile(List<Mechanic> mechanics, List<Order> orderList, List<Garage> garages) {
		serializator.saveToFile(new FacadeResultContainer(mechanics, orderList, garages));
	}

	/** Take list Box from serializer. */
	private void getListsFromDB() {
		resultContainer = serializator.getLists();
	}

}
