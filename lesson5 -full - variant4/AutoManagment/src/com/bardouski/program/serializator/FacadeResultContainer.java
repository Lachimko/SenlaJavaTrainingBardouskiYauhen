package com.bardouski.program.serializator;

import java.util.List;

import com.bardouski.program.model.Garage;
import com.bardouski.program.model.Mechanic;
import com.bardouski.program.model.Order;

public class FacadeResultContainer implements IResultContainer{

	private List<Mechanic> resultMechanics;
	private List<Order> resultOrders;
	private List<Garage> resultGarages;

	public FacadeResultContainer(List<Mechanic> resultMechanics, List<Order> resultOrders, List<Garage> resultGarages) {
		this.resultMechanics = resultMechanics;
		this.resultOrders = resultOrders;
		this.resultGarages = resultGarages;
	}

	public List<Mechanic> getResultMechanics() {
		return resultMechanics;
	}

	public void setResultMechanics(List<Mechanic> resultMechanics) {
		this.resultMechanics = resultMechanics;
	}

	public List<Order> getResultOrders() {
		return resultOrders;
	}

	public void setResultOrders(List<Order> resultOrders) {
		this.resultOrders = resultOrders;
	}

	public List<Garage> getResultGarages() {
		return resultGarages;
	}

	public void setResultGarages(List<Garage> resultGarages) {
		this.resultGarages = resultGarages;
	}

}
