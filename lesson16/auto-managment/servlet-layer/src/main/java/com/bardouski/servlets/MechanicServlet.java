package com.bardouski.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.impl.dto.MechanicDTO;
import com.bardouski.servlets.parents.JsonConvertable;

@WebServlet(urlPatterns = "/MechanicServlet")
public class MechanicServlet extends JsonConvertable {

	private static final String ID = "id";
	private static final String FULLNAME = "fullname";
	private static final long serialVersionUID = 1L;

	public MechanicServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer userId = Integer.parseInt(request.getParameter(ID));
		PrintWriter printWriter = response.getWriter();
		
		try {
			MechanicDTO returned = facade.getMechanic(userId);
			printWriter.print(prepareJson(returned));
		} catch (NoSuchObjectException e) {
			printWriter.print(NO_OBJECT);
		} catch (NumberFormatException e) {
			printWriter.print(ER_PARAM);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		facade.createMechanic(request.getParameter(FULLNAME));
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer userId = Integer.parseInt(request.getParameter(ID));
		PrintWriter printWriter = response.getWriter();
		
		try {
			facade.removeMechanic(userId);
		} catch (NoSuchObjectException e) {
			printWriter.print(NO_OBJECT);
		} catch (NumberFormatException e) {
			printWriter.print(ER_PARAM);
		}
	}

}
