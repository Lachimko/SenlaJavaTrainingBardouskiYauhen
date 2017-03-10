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
	private static final String FULL_NAME = "fullName";

	public MechanicDAO() {
		super(Mechanic.class);
	}
	
	public List<Mechanic> sortMechanicsByFullName(Session session) {
		return getAll(session, FULL_NAME);
	}

	public List<Mechanic> sortMechanicsByWork(Session session) {
		return getAll(session, ID_ORDER);
	}

	@Override
	public Mechanic findFreeMechanic(Session session) throws NoSuchObjectException {
		
		Criteria criteria = session.createCriteria(returnClass()).add(Restrictions.isNull("currentOrder"));
		return (Mechanic) criteria.setMaxResults(1).list().get(0);
	}

}
