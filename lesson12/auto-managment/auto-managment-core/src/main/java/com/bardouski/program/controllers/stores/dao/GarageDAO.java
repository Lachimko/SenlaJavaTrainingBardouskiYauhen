package com.bardouski.program.controllers.stores.dao;

import com.bardouski.controllers.dao.AbstractDAO;
import com.bardouski.controllers.dao.IGarageDAO;
import com.bardouski.model.impl.Garage;

public class GarageDAO extends AbstractDAO<Garage> implements IGarageDAO {

	public GarageDAO() {
		super(Garage.class);
	}

}
