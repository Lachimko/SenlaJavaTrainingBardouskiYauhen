package com.bardouski.controllers.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.bardouski.model.EntityModel;

public abstract class AbstractDAO<T extends EntityModel> {

	protected Logger logger = Logger.getLogger("DAO Layers");
	protected Class<T> clazz;
	
	public AbstractDAO(Class<T> clazz) {
		this.clazz = clazz;
	}

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
		
		String parameter = sortingColumn[0];
		if (!parameter.isEmpty()) {
			return session.createCriteria(returnClass()).add(Restrictions.isNotNull(parameter)).addOrder(Order.asc(parameter)).list();
		}
		return session.createCriteria(returnClass()).list();
	}

	public void update(Session session, T object) {
		session.update(object);
	}
	
	protected Class<T> returnClass(){
		return clazz;
	}

}
