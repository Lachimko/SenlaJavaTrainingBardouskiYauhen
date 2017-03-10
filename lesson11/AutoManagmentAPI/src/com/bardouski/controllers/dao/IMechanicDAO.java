package com.bardouski.controllers.dao;

import java.sql.Connection;
import java.util.List;

import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.impl.Mechanic;

public interface IMechanicDAO {

	Mechanic findFreeMechanic(Connection connection) throws NoSuchObjectException;

	List<Mechanic> sortMechanicsByFullName(Connection connection);

	List<Mechanic> sortMechanicsByWork(Connection connection);

}
