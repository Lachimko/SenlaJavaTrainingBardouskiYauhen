package com.bardouski.config;

/**Contains itself type values of properties*/
public class AutoManagmentPropertiesHolder {

	private String dbPath;
	private String dbCSVPath;
	private boolean addRemoveWorkplace;
	private boolean removeOrder;
	private boolean delayOrder;

	public AutoManagmentPropertiesHolder(String dbPath, String dbCSVPath, boolean addRemoveWorkplace,
			boolean removeOrder, boolean delayOrder) {
		this.dbPath = dbPath;
		this.dbCSVPath = dbCSVPath;
		this.addRemoveWorkplace = addRemoveWorkplace;
		this.removeOrder = removeOrder;
		this.delayOrder = delayOrder;
	}

	public String getDbPath() {
		return dbPath;
	}

	public String getDbCSVPath() {
		return dbCSVPath;
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
