package com.bardouski.model;

import java.util.Date;

public interface ITask extends EntityModel {

	String getToDo();

	void setToDo(String toDo);

	Date getRequestDate();

	void setRequestDate(Date requestDate);

	Date getStartDate();

	void setStartDate(Date startDate);

	Date getCompleteDate();

	void setCompleteDate(Date completeDate);

	Double getPrice();

	void setPrice(Double price);

	Integer getId();
	
	void setId(Integer id);
}
