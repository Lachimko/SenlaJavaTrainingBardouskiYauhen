package com.bardouski.dbprocessor;

import java.util.List;

import com.bardouski.dbprocessor.serializator.IResultContainer;
import com.bardouski.dbprocessor.serializator.ISerializer;
import com.bardouski.model.IGarage;
import com.bardouski.model.IMechanic;
import com.bardouski.model.IOrder;

public interface IDBProcessor {

	List<? extends IMechanic> getMechanicsDB();

	List<? extends IOrder> getOrdersDB();

	List<? extends IGarage> getGaragesDB();

	/** Push to FacadeResultContainer collections and throw to sateToFile */
	void saveToFile(IResultContainer resultContainer);

	/** Take list Box from serializer. */
	void getListsFromDB();

	/** Write String list to CSV */
	void exportCSV(List<? extends IMechanic> list);

	/** return Mechanic list, which was splited from CSV Strings */
	List<? extends IMechanic> importFromCSV();

	void setSerializator(ISerializer serializator);

	void setDbcsvProcessor(IDBCSVProcessor dbcsvProcessor);
}
