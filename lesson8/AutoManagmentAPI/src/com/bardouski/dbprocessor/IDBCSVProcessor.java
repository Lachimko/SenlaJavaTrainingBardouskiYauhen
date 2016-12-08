package com.bardouski.dbprocessor;

import java.util.List;

import com.bardouski.model.IMechanic;

public interface IDBCSVProcessor {

	/** return Mechanic list, which was splited from CSV Strings */
	List<? extends IMechanic> importCollection();

	/**
	 * Convert Mechanic collection from parameter to List<String> and run Method
	 * exportCollection
	 */
	void exportCSV(List<? extends IMechanic> list);

	void setPath(String path);
}
