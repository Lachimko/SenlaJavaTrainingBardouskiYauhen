package model;

import model.parents.Worker;

public class Cook extends Worker{
	
	private Order currentWork = null;
	
	public Cook(String name) {
		super(name);
	}

	public Order getCurrentWork() {
		return currentWork;
	}

	public void setCurrentWork(Order currentWork) {
		this.currentWork = currentWork;
	}


}
