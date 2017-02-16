package com.bardouski.controllers.stores;

import java.util.List;

import com.bardouski.dbprocessor.serializator.IResultContainer;
import com.bardouski.model.IMechanic;

public interface IStore {

	/** Serialize all collections in resultContainer */
	void saveToFile(IResultContainer resultContainer);

	/** Write String list to CSV */
	void exportCSV(List<? extends IMechanic> list);

	/** return Mechanic list, which was splited from CSV Strings */
	List<? extends IMechanic> importFromCSV();

}
