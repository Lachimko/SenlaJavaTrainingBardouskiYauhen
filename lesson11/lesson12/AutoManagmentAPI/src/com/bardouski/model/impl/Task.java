package com.bardouski.model.impl;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bardouski.annotationtask.annotations.Printable;
import com.bardouski.annotationtask.annotations.PrintableObject;
import com.bardouski.model.ITask;

@PrintableObject(name = "Task")
@Entity
@Table(name="task")
public class Task implements Serializable, ITask {

	private static final long serialVersionUID = 4735856939575291114L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_task")
	private Integer id;
	
	@Printable(name = "taskToDo", isDetailedOnly = false, order = 0)
	@Column(name="to_do")
	private String toDo;
	
	@Printable(name = "taskRequestDate", isDetailedOnly = false, order = 1)
	@Column(name="request_date")
	@Temporal(TemporalType.DATE)
	private Date requestDate;
	
	@Printable(name = "taskStartDate", isDetailedOnly = false, order = 2)
	@Column(name="start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Printable(name = "taskCompleteDate", isDetailedOnly = false, order = 3)
	@Column(name="complete_date")
	@Temporal(TemporalType.DATE)
	private Date completeDate;
	
	@Printable(name = "taskPrice", isDetailedOnly = false, order = 4)
	@Column(name="price")
	private double price;

	public Task() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String getToDo() {
		return toDo;
	}

	@Override
	public void setToDo(String toDo) {
		this.toDo = toDo;
	}

	@Override
	public Date getRequestDate() {
		return requestDate;
	}

	@Override
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	@Override
	public Date getStartDate() {
		return startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public Date getCompleteDate() {
		return completeDate;
	}

	@Override
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public void setPrice(double price) {
		this.price = price;
	}

}
