package com.bardouski.program.dbprocessor;

import java.util.List;

import com.bardouski.program.model.Mechanic;

public interface IDBCSVProcessor {

	/** return Mechanic list, which was splited from CSV Strings */
	List<Mechanic> importCollection();

	/**
	 * Convert Mechanic collection from parameter to List<String> and run Method
	 * exportCollection
	 */
	void exportCSV(List<Mechanic> list);

	void setPath(String path);
}
