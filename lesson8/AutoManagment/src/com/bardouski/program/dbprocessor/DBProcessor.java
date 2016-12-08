package com.bardouski.program.dbprocessor;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bardouski.config.ProgramProps;
import com.bardouski.dbprocessor.IDBCSVProcessor;
import com.bardouski.dbprocessor.IDBProcessor;
import com.bardouski.dbprocessor.serializator.IResultContainer;
import com.bardouski.dbprocessor.serializator.ISerializer;
import com.bardouski.model.IMechanic;
import com.bardouski.model.impl.Garage;
import com.bardouski.model.impl.Mechanic;
import com.bardouski.model.impl.Order;
import com.bardouski.program.dbprocessor.serializator.FacadeResultContainer;
import com.bardouski.program.dbprocessor.serializator.Serializator;
import com.bardouski.propertiesholder.PropertiesContext;

/**
 * Deserialize all collections from DB and save them to local lists. That lists
 * will shared with all stores.
 */
public class DBProcessor implements IDBProcessor {

	/** Container for transferring collection */
	private Logger logger = Logger.getLogger(this.getClass().getSimpleName());
	
	private FacadeResultContainer resultContainer;
	private Serializator serializer;
	private DBCSVProcessor dbcsvProcessor;

	public DBProcessor() {
		try {
			serializer = (Serializator) PropertiesContext.getInstance(ISerializer.class);
			serializer.setPath(ProgramProps.valueOf("dbPath"));
			dbcsvProcessor = (DBCSVProcessor) PropertiesContext.getInstance(IDBCSVProcessor.class);
			dbcsvProcessor.setPath(ProgramProps.valueOf("dbCSVPath"));
			getListsFromDB();
			
		} catch (ClassNotFoundException e) {
			logger.error(e.getClass());
		} catch (FileNotFoundException e) {
			logger.error(e.getClass());
		}

	}

	@Override
	public void setSerializator(ISerializer serializator) {
		this.serializer = (Serializator) serializator;
	}

	@Override
	public void setDbcsvProcessor(IDBCSVProcessor dbcsvProcessor) {
		this.dbcsvProcessor = (DBCSVProcessor) dbcsvProcessor;
	}

	@Override
	public List<Mechanic> getMechanicsDB() {
		return (resultContainer.getResultMechanics() == null) ? new ArrayList<>()
				: resultContainer.getResultMechanics();
	}

	@Override
	public List<Order> getOrdersDB() {
		return (resultContainer.getResultOrders() == null) ? new ArrayList<>() : resultContainer.getResultOrders();
	}

	@Override
	public List<Garage> getGaragesDB() {
		return (resultContainer.getResultGarages() == null) ? new ArrayList<>() : resultContainer.getResultGarages();
	}

	/** Push to FacadeResultContainer collections and throw to sateToFile */
	@Override
	public void saveToFile(IResultContainer resultContainer) {
		serializer.saveToFile(resultContainer);
	}

	/** Take list Box from serializer. */
	@Override
	public void getListsFromDB() {
		resultContainer = serializer.getLists();
	}

	/** Write String list to CSV */
	@Override
	public synchronized void exportCSV(List<? extends IMechanic> list) {
		dbcsvProcessor.exportCSV(list);
	}

	/** return Mechanic list, which was splited from CSV Strings */
	@Override
	public synchronized List<Mechanic> importFromCSV() {
		return dbcsvProcessor.importCollection();
	}

}
