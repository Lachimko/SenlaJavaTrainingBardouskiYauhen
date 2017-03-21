package com.bardouski.model.impl.dto;

import com.bardouski.model.dto.AutoMngmtDTO;
import com.bardouski.model.impl.WorkPlace;

public class WorkPlaceDTO implements AutoMngmtDTO {

	private static final long serialVersionUID = -2823107008398216104L;
	private Integer id;
	private Integer orderId;

	public WorkPlaceDTO() {
	}
	
	public WorkPlaceDTO(WorkPlace workPlace) {
		id = workPlace.getId();
		orderId = workPlace.getOrder().getId();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

}
