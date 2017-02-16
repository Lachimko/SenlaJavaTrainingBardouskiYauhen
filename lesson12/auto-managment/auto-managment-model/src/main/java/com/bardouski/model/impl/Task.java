package com.bardouski.model.impl;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bardouski.model.ITask;

@Entity
public class Task implements ITask {

	private static final long serialVersionUID = 4735856939575291114L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String toDo;

	@Temporal(TemporalType.DATE)
	private Date requestDate;

	@Temporal(TemporalType.DATE)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	private Date completeDate;

	private Double price;

	public Task() {
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
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
	public Double getPrice() {
		return price;
	}

	@Override
	public void setPrice(Double price) {
		this.price = price;
	}

}
