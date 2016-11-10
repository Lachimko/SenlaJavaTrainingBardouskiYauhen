package com.bardouski.program.controllers.stores;

import java.util.List;

import com.bardouski.program.dbprocessor.DBProcessor;
import com.bardouski.program.model.Garage;
import com.bardouski.program.model.Mechanic;
import com.bardouski.program.model.Order;

public class Store {
	
	protected DBProcessor dbProcessor = null;
	
	public void saveToFile(List<Mechanic> mechanics, List<Order> orderList, List<Garage> garages) {
		dbProcessor.saveToFile(mechanics, orderList, garages);
	}
	
}
