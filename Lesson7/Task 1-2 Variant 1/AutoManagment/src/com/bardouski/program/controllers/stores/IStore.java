package com.bardouski.program.controllers.stores;

import java.util.List;

import com.bardouski.program.dbprocessor.serializator.IResultContainer;
import com.bardouski.program.model.Mechanic;

public interface IStore {

	/** Serialize all collections in resultContainer */
	void saveToFile(IResultContainer resultContainer);

	/** Write String list to CSV */
	void exportCSV(List<Mechanic> list);

	/** return Mechanic list, which was splited from CSV Strings */
	List<Mechanic> importFromCSV();

}
