package com.bardouski.program.dbprocessor;

import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;

import com.bardouski.program.dbprocessor.serializator.FacadeResultContainer;
import com.bardouski.program.dbprocessor.serializator.Serializator;
import com.bardouski.program.exceptions.NoDBConnectionException;
import com.bardouski.program.model.Garage;
import com.bardouski.program.model.Mechanic;
import com.bardouski.program.model.Order;

/**
 * Deserialize all collections from DB and save them to local lists. That lists
 * will shared with all stores.
 */
public class DBProcessor {

	/** Container for transferring collection*/
	private FacadeResultContainer resultContainer;
	
	private Serializator serializator;
	private DBCSVProcessor dbcsvProcessor;

	// Constructors, Getters/Setters
	public DBProcessor(String dbPath, String dbCSVPath) throws NoDBConnectionException, FileNotFoundException {

		try {
			serializator = new Serializator(dbPath);
			getListsFromDB();
			dbcsvProcessor = new DBCSVProcessor(dbCSVPath);

		} catch (FileNotFoundException e) {
			try{
				dbcsvProcessor = new DBCSVProcessor(dbCSVPath);
			} catch (InvalidPathException e1){
				throw new NoDBConnectionException();
			}
		}
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
	public void saveToFile(FacadeResultContainer resultContainer) {
		serializator.saveToFile(resultContainer);
	}

	/** Take list Box from serializer. */
	private void getListsFromDB() {
		resultContainer = serializator.getLists();
	}
	
	/** Write String list to CSV */
	public void exportCSV(List<Mechanic> list) {
		dbcsvProcessor.exportCSV(list);
	}

	/** return Mechanic list, which was splited from CSV Strings */
	public List<Mechanic> importFromCSV() {
		return dbcsvProcessor.importCollection();
	}
	
	
	
}
