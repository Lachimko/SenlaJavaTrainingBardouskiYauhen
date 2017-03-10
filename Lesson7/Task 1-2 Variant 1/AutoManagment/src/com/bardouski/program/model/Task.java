package com.bardouski.program.model;

import java.io.Serializable;
import java.util.Date;

import com.bardouski.annotationtask.annotations.Printable;
import com.bardouski.annotationtask.annotations.PrintableObject;

@PrintableObject(name = "Task")
public class Task implements Serializable, ITask {

	private static final long serialVersionUID = 4735856939575291114L;

	@Printable(name = "taskToDo", isDetailedOnly = false, order = 0)
	private String toDo;
	@Printable(name = "taskRequestDate", isDetailedOnly = false, order = 1)
	private Date requestDate;
	@Printable(name = "taskStartDate", isDetailedOnly = false, order = 2)
	private Date startDate;
	@Printable(name = "taskCompleteDate", isDetailedOnly = false, order = 3)
	private Date completeDate;
	@Printable(name = "taskPrice", isDetailedOnly = false, order = 4)
	private double price;

	public Task(String toDo, Date requestDate, Date startDate, Date completeDate, double price) {
		this.toDo = toDo;
		this.requestDate = requestDate;
		this.startDate = startDate;
		this.completeDate = completeDate;
		this.price = price;
	}

	public Task() {
	}

	public String getToDo() {
		return toDo;
	}

	public void setToDo(String toDo) {
		this.toDo = toDo;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
