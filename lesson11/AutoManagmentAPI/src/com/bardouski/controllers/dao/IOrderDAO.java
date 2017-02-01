package com.bardouski.controllers.dao;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

import com.bardouski.model.impl.Order;
import com.bardouski.model.impl.WorkPlace;
import com.bardouski.model.impl.enums.OrderStatus;

public interface IOrderDAO {

	List<Order> getAll(Connection connection, OrderStatus orderStatus);

	List<Order> sortOrdersByCompleteDateAction(Connection connection);

	List<Order> sortOrdersByPriceAction(Connection connection);

	List<Order> sortOrdersByRequestDateAction(Connection connection);

	List<Order> sortOrdersByStartDateAction(Connection connection);

	List<WorkPlace> getFreePlacesInDate(Connection connection, Date date);
}
