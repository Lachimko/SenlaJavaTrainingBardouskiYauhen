package com.bardouski.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.IOrder;
import com.bardouski.servlets.parents.JsonConvertable;

public class TicketServlet extends JsonConvertable {

	private static final long serialVersionUID = 1L;

	public TicketServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer ticketId = Integer.parseInt(request.getParameter("id"));
		try {
			IOrder returned = facade.getOrder(ticketId);
			response.getWriter().print(prepareJson(returned));
		} catch (NoSuchObjectException ignore) {
			ignore.printStackTrace(); // Ignored Exception
			response.getWriter().print(NULL);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer ticketId = Integer.parseInt(request.getParameter("id"));
		try {
			facade.removeOrder(ticketId);;
		} catch (NoSuchObjectException ignore) {
			ignore.printStackTrace();// Ignored Exception
		}
	}

}
