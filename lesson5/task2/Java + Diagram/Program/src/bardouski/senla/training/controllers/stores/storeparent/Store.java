package bardouski.senla.training.controllers.stores.storeparent;

import java.util.List;

import bardouski.senla.training.dbprocessor.DBProcessor;
import bardouski.senla.training.model.Garage;
import bardouski.senla.training.model.Mechanic;
import bardouski.senla.training.model.Order;

public class Store {
	
	protected static DBProcessor dbProcessor;
	
	private List<Order> ordersLink;
	private List<Garage> garagesLink;
	private List<Mechanic> mechanicsLink;

	protected Store(){
		
	}
	
	protected void saveToDB(List<Order> orders, List<Garage> garages, List<Mechanic> mechanics){
		dbProcessor.writeToDB(ordersLink, garagesLink, mechanicsLink);
	}

	public void setOrdersLink(List<Order> ordersLink) {
		this.ordersLink = ordersLink;
	}

	public void setGaragesLink(List<Garage> garagesLink) {
		this.garagesLink = garagesLink;
	}

	public void setMechanicsLink(List<Mechanic> mechanicsLink) {
		this.mechanicsLink = mechanicsLink;
	}
	
}
