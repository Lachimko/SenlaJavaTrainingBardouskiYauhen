package com.bardouski.program.controllers.services;

import org.hibernate.Session;

import com.bardouski.hibernate.initializer.HibernateUtil;

public class SessionReturnable {

	protected Session getSession() {
		return HibernateUtil.getInstance().getSession();
	}

}
