package model;

public class WorkPlace {

	private String identifier;
	private Task task;
	private Mechanic mechanic;

	public WorkPlace(String title) {
		this.identifier = title;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public void setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String title) {
		this.identifier = title;
	}

}
