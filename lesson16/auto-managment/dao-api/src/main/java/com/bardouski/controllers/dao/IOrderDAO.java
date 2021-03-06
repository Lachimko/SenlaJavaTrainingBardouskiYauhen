package com.bardouski.controllers.dao;

import java.util.List;

import org.hibernate.Session;

import com.bardouski.model.impl.Ticket;
import com.bardouski.model.impl.enums.OrderStatus;

public interface IOrderDAO {

	List<Ticket> getAll(Session session, OrderStatus orderStatus);

}
