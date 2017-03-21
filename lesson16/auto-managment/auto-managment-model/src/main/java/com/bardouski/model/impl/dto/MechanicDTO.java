package com.bardouski.model.impl.dto;

import com.bardouski.model.dto.AutoMngmtDTO;
import com.bardouski.model.impl.Mechanic;

public class MechanicDTO implements AutoMngmtDTO {

	private static final long serialVersionUID = -1256372256008341555L;

	private Integer id;
	private String fullName;
	private Integer currentOrderId;

	public MechanicDTO(Mechanic mechanic) {
		id = mechanic.getId();
		fullName = mechanic.getFullName();
		currentOrderId = (mechanic.getCurrentOrder() == null) ? null : mechanic.getCurrentOrder().getId();
	}

	public MechanicDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getCurrentOrderId() {
		return currentOrderId;
	}

	public void setCurrentOrderId(Integer currentOrderId) {
		this.currentOrderId = currentOrderId;
	}

}
