package com.bardouski.program.controllers.stores;

import java.util.ArrayList;
import java.util.List;

import com.bardouski.program.dbprocessor.DBProcessor;
import com.bardouski.program.impexp.ImportExport;
import com.bardouski.program.model.Garage;
import com.bardouski.program.model.Mechanic;
import com.bardouski.program.model.Order;

public class Store {

	protected DBProcessor dbProcessor = null;
	protected ImportExport importExport = new ImportExport();

	public void saveToFile(List<Mechanic> mechanics, List<Order> orderList, List<Garage> garages) {
		dbProcessor.saveToFile(mechanics, orderList, garages);
	}

	/** Write String list to CSV */
	public void exportCSV(List<Mechanic> list) {

		List<String> listToExport = new ArrayList<>();

		for (Mechanic mechanic : list) {
			String stringToCsv = new StringBuilder().append(mechanic.getId()).append(",").append(mechanic.getFullName())
					.append(",").toString();
			listToExport.add(stringToCsv);
		}
		importExport.exportCollection(listToExport);
	}

	/** return Mechanic list, which was splited from CSV Strings */
	public List<Mechanic> importFromCSV() {
		return importExport.importCollection();
	}
}
