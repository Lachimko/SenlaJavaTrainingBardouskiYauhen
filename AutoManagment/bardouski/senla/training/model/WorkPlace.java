package bardouski.senla.training.model;

public class WorkPlace {

	private static int idCount = 1;
	
	private int id;
	private String identifier;
	private Task task;
	private Mechanic mechanic;

	//converter constructor without id increment
	public WorkPlace(int id, String identifier) {
		this.id = id;
		this.identifier = identifier;
	}
	
	//converter constructor without id increment
	public WorkPlace(int id, String identifier, Task task, Mechanic mechanic) {
		this.id = id;
		this.identifier = identifier;
		this.task = task;
		this.mechanic = mechanic;
	}

	public WorkPlace(String title) {
		this.id = idCount;
		idCount++;
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
