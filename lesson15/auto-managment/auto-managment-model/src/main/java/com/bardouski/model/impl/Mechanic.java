package com.bardouski.model.impl;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.bardouski.model.IMechanic;
import com.bardouski.model.IOrder;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Mechanic implements IMechanic {

	private static final long serialVersionUID = -1256372256008341555L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String fullName;

	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ticket_id", nullable = true)
	private Ticket currentOrder;

	public Mechanic(String fullName) {
		this.fullName = fullName;
	}

	public Mechanic() {
	}

	@Override
	public String getFullName() {
		return fullName;
	}

	@Override
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Override
	public Ticket getCurrentOrder() {
		return currentOrder;
	}

	@Override
	public void setCurrentOrder(IOrder currentOrder) {
		this.currentOrder = (Ticket) currentOrder;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

}
