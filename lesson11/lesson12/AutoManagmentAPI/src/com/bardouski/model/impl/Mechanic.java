package com.bardouski.model.impl;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.bardouski.annotationtask.annotations.Printable;
import com.bardouski.annotationtask.annotations.PrintableObject;
import com.bardouski.annotationtask.annotations.PrintableRef;
import com.bardouski.model.IMechanic;
import com.bardouski.model.IOrder;

@PrintableObject(name = "Mechanic")
@Entity
public class Mechanic implements Serializable, IMechanic {

	private static final long serialVersionUID = -1256372256008341555L;

	@Printable(name = "mechanicID", isDetailedOnly = false, order = 0)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Printable(name = "mechanicFullName", isDetailedOnly = false, order = 1)
	@Column(name = "full_name")
	private String fullName;

	@PrintableRef(name = "mechanicOrder", order = 2)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_ticket", nullable = true)
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
