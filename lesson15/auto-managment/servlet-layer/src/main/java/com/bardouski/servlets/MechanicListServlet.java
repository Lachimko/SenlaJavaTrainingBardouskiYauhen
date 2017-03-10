package com.bardouski.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bardouski.servlets.parents.JsonConvertable;

public class MechanicListServlet extends JsonConvertable {

	private static final long serialVersionUID = 1L;
	private static final String SORTING = "sorting";

	public MechanicListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sortingparam = request.getParameter(SORTING);
		
		switch (sortingparam.toLowerCase()) {
		case "all":
			prepareJson(facade.getAllMechanics());
			break;

		case "name":
			prepareJson(facade.sortMechanicsByFullName());
			break;

		case "work":
			prepareJson(facade.sortMechanicsByCurrentWork());
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
