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

	public List<Mechanic> sortMechanicsByFullName(Session session) {
		return getAll(session, FULL_NAME);
	}

	public List<Mechanic> sortMechanicsByWork(Session session) {
		return getAll(session, ID_ORDER);
	}

	@Override
	public Mechanic findFreeMechanic(Session session) throws NoSuchObjectException {
		
		Criteria criteria = session.createCriteria(getClass()).add(Restrictions.isNull("currentOrder"));
		criteria.setMaxResults(1);
		return (Mechanic) criteria.list().get(0);
	}
	
	@Override
	protected Class<Mechanic> returnClass() {
		return Mechanic.class;
	}

}
