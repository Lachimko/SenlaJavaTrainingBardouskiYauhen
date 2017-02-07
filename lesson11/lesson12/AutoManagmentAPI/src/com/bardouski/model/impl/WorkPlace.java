package com.bardouski.model.impl;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.bardouski.annotationtask.annotations.Printable;
import com.bardouski.annotationtask.annotations.PrintableObject;
import com.bardouski.annotationtask.annotations.PrintableRef;
import com.bardouski.model.IOrder;
import com.bardouski.model.IWorkPlace;

@PrintableObject(name = "WorkPlace")
@Entity
public class WorkPlace implements IWorkPlace, Serializable {

	private static final long serialVersionUID = -2823107008398216104L;
	
	@Printable(name = "workPlaceID", isDetailedOnly = false, order = 0)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JoinColumn(name="id_garage")
	private Garage garage;
	
	@PrintableRef(name = "workPlaceOrder", order = 1)
	@JoinColumn(name = "id_ticket", nullable = true)
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

	public Garage getGarage() {
		return garage;
	}

	public void setGarage(Garage garage) {
		this.garage = garage;
	}

	public void setOrder(Ticket order) {
		this.order = order;
	}
	
	

}
