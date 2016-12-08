package com.bardouski.program.controllers.stores;

import java.io.FileNotFoundException;
import java.util.List;

import com.bardouski.controllers.stores.IStore;
import com.bardouski.dbprocessor.IDBProcessor;
import com.bardouski.dbprocessor.serializator.IResultContainer;
import com.bardouski.exceptions.NoDBConnectionException;
import com.bardouski.model.IMechanic;
import com.bardouski.model.impl.Mechanic;
import com.bardouski.program.dbprocessor.DBProcessor;
import com.bardouski.propertiesholder.PropertiesContext;

public class Store implements IStore {

	protected static DBProcessor dbProcessor = null;

	public Store() throws FileNotFoundException, NoDBConnectionException, ClassNotFoundException {
		if (dbProcessor == null){
			dbProcessor = (DBProcessor) PropertiesContext.getInstance(IDBProcessor.class); 
		}
	}

	/** Serialize all collections in resultContainer */
	@Override
	public void saveToFile(IResultContainer resultContainer) {
		dbProcessor.saveToFile(resultContainer);
	}

	/** Write String list to CSV */
	@Override
	public synchronized void exportCSV(List<? extends IMechanic> list) {
		dbProcessor.exportCSV(list);
	}

	/** return Mechanic list, which was splited from CSV Strings */
	@Override
	public synchronized List<Mechanic> importFromCSV() {
		return dbProcessor.importFromCSV();
	}

}
