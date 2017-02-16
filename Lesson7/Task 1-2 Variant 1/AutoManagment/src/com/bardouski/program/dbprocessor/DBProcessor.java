package com.bardouski.program.dbprocessor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.bardouski.program.dbprocessor.serializator.IResultContainer;
import com.bardouski.program.dbprocessor.serializator.ISerializer;
import com.bardouski.program.dbprocessor.serializator.Serializator;
import com.bardouski.program.exceptions.NoDBConnectionException;
import com.bardouski.program.model.Garage;
import com.bardouski.program.model.Mechanic;
import com.bardouski.program.model.Order;
import com.bardouski.propertiesholder.PropertiesContext;

/**
 * Deserialize all collections from DB and save them to local lists. That lists
 * will shared with all stores.
 */
public class DBProcessor implements IDBProcessor {

	/** Container for transferring collection */
	private IResultContainer resultContainer;

	private ISerializer serializator;
	private IDBCSVProcessor dbcsvProcessor;

	// Constructors, Getters/Setters
	public DBProcessor() {
	}

	public DBProcessor(String dbPath, String dbCSVPath) throws NoDBConnectionException, FileNotFoundException, ClassNotFoundException {

		serializator = (Serializator) PropertiesContext.getInstance(ISerializer.class);
		serializator.setPath(dbPath);
		getListsFromDB();
		dbcsvProcessor = (DBCSVProcessor) PropertiesContext.getInstance(IDBCSVProcessor.class);
		dbcsvProcessor.setPath(dbCSVPath);
	}

	public void setSerializator(ISerializer serializator) {
		this.serializator = serializator;
	}

	public void setDbcsvProcessor(IDBCSVProcessor dbcsvProcessor) {
		this.dbcsvProcessor = dbcsvProcessor;
	}

	public List<Mechanic> getMechanicsDB() {
		return (resultContainer.getResultMechanics() == null) ? new ArrayList<>()
				: resultContainer.getResultMechanics();
	}

	public List<Order> getOrdersDB() {
		return (resultContainer.getResultOrders() == null) ? new ArrayList<>() : resultContainer.getResultOrders();
	}

	public List<Garage> getGaragesDB() {
		return (resultContainer.getResultGarages() == null) ? new ArrayList<>() : resultContainer.getResultGarages();
	}
	// END Constructors, Getters/Setters

	/** Push to FacadeResultContainer collections and throw to sateToFile */
	public void saveToFile(IResultContainer resultContainer) {
		serializator.saveToFile(resultContainer);
	}

	/** Take list Box from serializer. */
	public void getListsFromDB() {
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
