package com.bardouski.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IMechanic;
import com.bardouski.servlets.parents.JsonConvertable;

public class MechanicServlet extends JsonConvertable {

	private static final long serialVersionUID = 1L;

	public MechanicServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer userId = Integer.parseInt(request.getParameter("id"));
		try {
			IMechanic returned = facade.getMechanic(userId);
			response.getWriter().print(prepareJson(returned));
		} catch (NoSuchObjectException ignore) {
			ignore.printStackTrace(); // Ignored Exception
			response.getWriter().print(NULL);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		facade.createMechanic(request.getParameter("name"));
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer userId = Integer.parseInt(request.getParameter("id"));
		try {
			facade.removeMechanic(userId);
		} catch (NoSuchObjectException ignore) {
			ignore.printStackTrace();// Ignored Exception
		}
	}

}
