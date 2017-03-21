package com.bardouski.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bardouski.servlets.parents.JsonConvertable;

@WebServlet(urlPatterns="/MechanicListServlet")
public class MechanicListServlet extends JsonConvertable {

	private static final long serialVersionUID = 1L;
	private static final String WORK_KEYWORD = "currentOrder";
	private static final String NAME_KEYWORD = "fullName";
	private static final String ALL_KEYWORD = "";
	private static final String SORTING = "sorting";
	
	public MechanicListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sortingParam = request.getParameter(SORTING);
		PrintWriter writer = response.getWriter();
		
		System.out.println(sortingParam);
		if (sortingParam.equals(ALL_KEYWORD) || sortingParam.equals(NAME_KEYWORD) || sortingParam.equals(WORK_KEYWORD)){
			writer.print(prepareJson(facade.getAllMechanics(sortingParam)));
		} else {
			writer.print(ER_PARAM);
		}
	}

}
