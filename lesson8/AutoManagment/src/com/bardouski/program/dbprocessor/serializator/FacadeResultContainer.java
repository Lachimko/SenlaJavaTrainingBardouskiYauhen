package com.bardouski.program.dbprocessor.serializator;

import java.util.List;

import com.bardouski.dbprocessor.serializator.IResultContainer;
import com.bardouski.model.IGarage;
import com.bardouski.model.IMechanic;
import com.bardouski.model.IOrder;
import com.bardouski.program.model.Garage;
import com.bardouski.program.model.Mechanic;
import com.bardouski.program.model.Order;

/** Container for collections transfered from Serializator to Stores */
public class FacadeResultContainer implements IResultContainer {

	private List<Mechanic> resultMechanics;
	private List<Order> resultOrders;
	private List<Garage> resultGarages;

	@SuppressWarnings("unchecked")
	public FacadeResultContainer(List<? extends IMechanic> resultMechanics, List<? extends IOrder> resultOrders,
			List<? extends IGarage> resultGarages) {
		this.resultMechanics = (List<Mechanic>) resultMechanics;
		this.resultOrders = (List<Order>) resultOrders;
		this.resultGarages = (List<Garage>) resultGarages;
	}

	@Override
	public List<Mechanic> getResultMechanics() {
		return resultMechanics;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setResultMechanics(List<? extends IMechanic> resultMechanics) {
		this.resultMechanics = (List<Mechanic>) resultMechanics;
	}

	@Override
	public List<Order> getResultOrders() {
		return resultOrders;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setResultOrders(List<? extends IOrder> resultOrders) {
		this.resultOrders = (List<Order>) resultOrders;
	}

	@Override
	public List<Garage> getResultGarages() {
		return resultGarages;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setResultGarages(List<? extends IGarage> resultGarages) {
		this.resultGarages = (List<Garage>) resultGarages;
	}

}
