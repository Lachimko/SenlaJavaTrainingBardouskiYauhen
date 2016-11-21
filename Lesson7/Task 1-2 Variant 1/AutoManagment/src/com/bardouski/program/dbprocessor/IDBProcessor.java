package com.bardouski.program.dbprocessor;

import java.util.List;

import com.bardouski.program.dbprocessor.serializator.IResultContainer;
import com.bardouski.program.dbprocessor.serializator.ISerializer;
import com.bardouski.program.model.Garage;
import com.bardouski.program.model.Mechanic;
import com.bardouski.program.model.Order;

public interface IDBProcessor {

	List<Mechanic> getMechanicsDB();

	List<Order> getOrdersDB();

	List<Garage> getGaragesDB();

	/** Push to FacadeResultContainer collections and throw to sateToFile */
	void saveToFile(IResultContainer resultContainer);

	/** Take list Box from serializer. */
	void getListsFromDB();

	/** Write String list to CSV */
	void exportCSV(List<Mechanic> list);

	/** return Mechanic list, which was splited from CSV Strings */
	List<Mechanic> importFromCSV();

	void setSerializator(ISerializer serializator);

	void setDbcsvProcessor(IDBCSVProcessor dbcsvProcessor);
}
