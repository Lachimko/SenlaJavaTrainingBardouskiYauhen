package com.bardouski.controllers.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public abstract class AbstractDAO<T> {

	protected Logger logger = Logger.getLogger("DAO Layers");
	protected static final String SQL_ERROR = "SQL Error";

	protected abstract Class<T> returnClass();

	/** Create Instance in DB */
	public void create(Session session, T object) {
		session.save(object);
	}
	
	public void saveOrUpdate(Session session, T object){
		session.saveOrUpdate(object);
	}

	/** Return an instance found by id */
	@SuppressWarnings("unchecked")
	public T getById(Session session, Integer id) {
		return (T) session.get(returnClass(), id);
	}

	/** Return proxy and do not hit the db. */
	@SuppressWarnings("unchecked")
	public T getProxyById(Session session, Integer id) throws ObjectNotFoundException {
		return (T) session.load(returnClass(), id);
	}

	/** Remove instance from DB */
	public void delete(Session session, T object) {
		session.delete(object);
	}

	/**
	 * Return all instances list if no parameters exist. Return ordered list if
	 * parameter exist
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll(Session session, String... sortingColumn) {
		if (sortingColumn.length > 0) {
			return session.createCriteria(returnClass()).add(Restrictions.isNotNull(sortingColumn[0])).addOrder(Order.asc(sortingColumn[0])).list();
		}
		return session.createCriteria(returnClass()).list();
	}

	public void update(Session session, T object) {
		session.update(object);
	}

}
