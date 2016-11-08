package bardouski.senla.training.controllers.stores;

import java.util.ArrayList;
import java.util.List;

import bardouski.senla.training.dbprocessor.DBProcessor;
import bardouski.senla.training.model.Garage;
import bardouski.senla.training.model.Mechanic;
import bardouski.senla.training.model.Order;

public class Store {
	
	protected DBProcessor dbProcessor;
	
	@SuppressWarnings("rawtypes")
	protected void saveToDB(List<Order> orders, List<Garage> garages, List<Mechanic> mechanics){
		List<List> list = new ArrayList<>();
		list.add(orders);
		list.add(garages);
		list.add(mechanics);
		dbProcessor.writeToDB(list);
	}

	public DBProcessor getDbProcessor() {
		return dbProcessor;
	}

	public void setDbProcessor(DBProcessor dbProcessor) {
		this.dbProcessor = dbProcessor;
	}
	
}
