package model;

public class Task {
	
	private String toDo;
	private String requestDate;
	private String startDate;
	private String completeDate;
	private double price;
	
	public Task(String toDo, String requestDate) {
		this.toDo = toDo;
		this.requestDate = requestDate;
	}

	public Task(String toDo, String requestDate, String startDate, String completeDate, double price) {
		this.toDo = toDo;
		this.requestDate = requestDate;
		this.startDate = startDate;
		this.completeDate = completeDate;
		this.price = price;
	}

	public String getToDo() {
		return toDo;
	}

	public void setToDo(String toDo) {
		this.toDo = toDo;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
