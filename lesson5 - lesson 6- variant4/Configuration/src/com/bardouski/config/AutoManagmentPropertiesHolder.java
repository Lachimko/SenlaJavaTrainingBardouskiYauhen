package com.bardouski.config;

/**Contains itself type values of properties*/
public class AutoManagmentPropertiesHolder implements IPropertiesHolder{

	private String dbPath;
	private boolean addRemoveWorkplace;
	private boolean removeOrder;
	private boolean delayOrder;

	public AutoManagmentPropertiesHolder(String dbPath, boolean addRemoveWorkplace, boolean removeOrder,
			boolean delayOrder) {
		this.dbPath = dbPath;
		this.addRemoveWorkplace = addRemoveWorkplace;
		this.removeOrder = removeOrder;
		this.delayOrder = delayOrder;
	}

	public String getDbPath() {
		return dbPath;
	}

	public boolean isAddRemoveWorkplace() {
		return addRemoveWorkplace;
	}

	public boolean isRemoveOrder() {
		return removeOrder;
	}

	public boolean isDelayOrder() {
		return delayOrder;
	}
	
	

}
