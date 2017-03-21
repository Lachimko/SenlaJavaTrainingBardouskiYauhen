package com.bardouski.program.controllers.stores.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.bardouski.controllers.dao.AbstractDAO;
import com.bardouski.controllers.dao.IMechanicDAO;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.impl.Mechanic;

public class MechanicDAO extends AbstractDAO<Mechanic> implements IMechanicDAO {

	private static final String ID_ORDER = "currentOrder";

	public MechanicDAO() {
		super(Mechanic.class);
	}
	
	public List<Mechanic> getAllMechanics(Session session, String sortParameter) {
		return getAll(session, sortParameter);
	}
	
	@Override
	public Mechanic findFreeMechanic(Session session) throws NoSuchObjectException {
		
		Criteria criteria = session.createCriteria(returnClass()).add(Restrictions.isNull(ID_ORDER));
		return (Mechanic) criteria.setMaxResults(1).list().get(0);
	}

}
