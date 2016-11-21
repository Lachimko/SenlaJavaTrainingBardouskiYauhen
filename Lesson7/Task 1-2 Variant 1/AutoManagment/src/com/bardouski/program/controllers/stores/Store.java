package com.bardouski.program.controllers.stores;

import java.util.List;

import com.bardouski.program.dbprocessor.DBProcessor;
import com.bardouski.program.dbprocessor.serializator.IResultContainer;
import com.bardouski.program.model.Mechanic;

public class Store implements IStore{

	protected static DBProcessor dbProcessor = null;

	/**Serialize all collections in resultContainer */
	public void saveToFile(IResultContainer resultContainer) {
		dbProcessor.saveToFile(resultContainer);
	}

	/** Write String list to CSV */
	public void exportCSV(List<Mechanic> list) {
		dbProcessor.exportCSV(list);
	}

	/** return Mechanic list, which was splited from CSV Strings */
	public List<Mechanic> importFromCSV() {
		return dbProcessor.importFromCSV();
	}

}
