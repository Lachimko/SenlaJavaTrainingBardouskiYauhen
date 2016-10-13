package controllers;

import model.Task;

public class TaskManager {

	private static TaskManager instance;

	private Task currentTask;

	private TaskManager() {
	}

	public void setCurrentTask(Task currentTask) {
		
		this.currentTask = currentTask == null ? null : currentTask;
	}

	public Task createTask(String toDo, String startDate, String completeDate, double price) {

		String requestDate = "10-13-206";

		Task currentTask = new Task(toDo, requestDate, startDate, completeDate, price);

		this.currentTask = currentTask;

		return currentTask;
	}

	public static TaskManager getInstance() {

		if (instance == null) {
			return instance = new TaskManager();
		}
		return instance;
	}

	public void viewTask() {

		if (currentTask != null) {

			StringBuilder sb = new StringBuilder();

			sb.append("ToDo: ").append(currentTask.getToDo()).append(" Request Date: ")
					.append(currentTask.getRequestDate());
			sb.append(" Start Date: ").append(currentTask.getStartDate()).append(" CompleteDate: ")
					.append(currentTask.getCompleteDate());
			sb.append(" Price: ").append(currentTask.getPrice()).append("\n");

			System.out.println(sb);
		} else {
			System.out.println("No current task");
		}
	}

}
