package com.bardouski.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.impl.dto.TicketDTO;
import com.bardouski.servlets.parents.JsonConvertable;

@WebServlet(urlPatterns="/TicketServlet")
public class TicketServlet extends JsonConvertable {

	private static final String ID = "id";
	private static final long serialVersionUID = 1L;

	public TicketServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter printWriter = response.getWriter();
		try {
			Integer ticketId = Integer.parseInt(request.getParameter(ID));
			TicketDTO returned = facade.getOrder(ticketId);
			response.getWriter().print(prepareJson(returned));
		} catch (NoSuchObjectException e) {
			reportError(e, NO_OBJECT, printWriter);
		} catch (NumberFormatException e) {
			reportError(e, JSON_ERROR, printWriter);
		}
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer ticketId = Integer.parseInt(request.getParameter(ID));
		PrintWriter printWriter = response.getWriter();
		
		try {
			facade.removeOrder(ticketId);
		} catch (NoSuchObjectException e) {
			reportError(e, NO_OBJECT, printWriter);
		}
	}

}
