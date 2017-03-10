package com.bardouski.program.controllers.stores.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.bardouski.controllers.dao.AbstractDAO;
import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.impl.Garage;
import com.bardouski.model.impl.WorkPlace;

public class WorkPlaceDAO extends AbstractDAO<WorkPlace> {

	private static final String COMPLETE_DATE_FIELD = "task.completeDate";
	private static final String TASK_ALIAS = "task";
	private static final String TASK_OF_ORDER = "order.task";
	private static final String ORDER_ALIAS = "order";
	private static final String ORDER_OF_WORKPLACE = "workplace.order";
	private static final String WORKPLACE_ALIAS = "workplace";
	private static final String FREE_PLACE_PATTERN = ORDER_ALIAS;

	public WorkPlaceDAO() {
		super(WorkPlace.class);
	}

	public WorkPlace getFreePlace(Session session) throws NoSuchObjectException {
		Criteria criteria = session.createCriteria(returnClass()).add(Restrictions.isNull(FREE_PLACE_PATTERN));
		return (WorkPlace) criteria.setMaxResults(1).list().get(0);
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<WorkPlace> getFreePlacesInDate(Session session, Date date) {

		Criteria criteria = session.createCriteria(returnClass(), WORKPLACE_ALIAS)
				.createAlias(ORDER_OF_WORKPLACE, ORDER_ALIAS, Criteria.LEFT_JOIN)
				.createAlias(TASK_OF_ORDER, TASK_ALIAS, Criteria.LEFT_JOIN);

		criteria.add(Restrictions.or(Restrictions.isNull(ORDER_ALIAS), Restrictions.le(COMPLETE_DATE_FIELD, date)));

		return criteria.list();
	}

	
	public void removeWorkPlaceInGarage(Session session, Garage garage, int placeID) {
		WorkPlace tempObject = getById(session, new Integer(placeID));
		garage.getWorkPlaces().remove(tempObject);
	}
}
