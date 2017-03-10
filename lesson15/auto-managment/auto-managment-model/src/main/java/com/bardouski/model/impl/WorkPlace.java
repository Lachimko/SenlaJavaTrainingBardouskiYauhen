package com.bardouski.model.impl;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.bardouski.model.IOrder;
import com.bardouski.model.IWorkPlace;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class WorkPlace implements IWorkPlace {

	private static final long serialVersionUID = -2823107008398216104L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ticket_id", nullable = true)
	private Ticket order;

	public WorkPlace() {
	}

	@Override
	public Ticket getOrder() {
		return order;
	}

	@Override
	public void setOrder(IOrder order) {
		this.order = (Ticket) order;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrder(Ticket order) {
		this.order = order;
	}

}
