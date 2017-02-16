package com.bardouski.controllers.dao;

import java.util.List;

import org.hibernate.Session;
import com.bardouski.model.impl.Mechanic;

public interface IMechanicDAO {

	Mechanic findFreeMechanic(Session session) throws Exception;

	List<Mechanic> sortMechanicsByFullName(Session session);

	List<Mechanic> sortMechanicsByWork(Session session);

}