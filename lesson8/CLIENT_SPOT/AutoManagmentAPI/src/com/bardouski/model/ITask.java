package com.bardouski.model;

import java.util.Date;

public interface ITask {

	String getToDo();

	void setToDo(String toDo);

	Date getRequestDate();

	void setRequestDate(Date requestDate);

	Date getStartDate();

	void setStartDate(Date startDate);

	Date getCompleteDate();

	void setCompleteDate(Date completeDate);

	double getPrice();

	void setPrice(double price);

}
