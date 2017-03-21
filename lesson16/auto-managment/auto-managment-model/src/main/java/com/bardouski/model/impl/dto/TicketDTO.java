package com.bardouski.model.impl.dto;

import javax.persistence.Entity;

import com.bardouski.model.dto.AutoMngmtDTO;
import com.bardouski.model.impl.Task;
import com.bardouski.model.impl.Ticket;
import com.bardouski.model.impl.enums.OrderStatus;

@Entity
public class TicketDTO implements AutoMngmtDTO {

	private static final long serialVersionUID = -3408744967081589710L;

	private Integer id;
	private String mechanicFullName;
	private Integer workPlaceId;
	private Task task;
	private OrderStatus orderStatus;

	public TicketDTO(){
	}
	
	public TicketDTO(Ticket ticket){
		id = ticket.getId();
		mechanicFullName = ticket.getMechanic().getFullName();
		workPlaceId = ticket.getWorkPlace().getId();
		task = ticket.getTask();
		orderStatus = ticket.getOrderStatus();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMechanicFullName() {
		return mechanicFullName;
	}

	public void setMechanicFullName(String mechanicFullName) {
		this.mechanicFullName = mechanicFullName;
	}

	public Integer getWorkPlaceId() {
		return workPlaceId;
	}

	public void setWorkPlaceId(Integer workPlaceId) {
		this.workPlaceId = workPlaceId;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

}
