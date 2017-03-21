package com.bardouski.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bardouski.exceptions.NoSuchObjectException;
import com.bardouski.model.impl.dto.MechanicDTO;
import com.bardouski.model.impl.dto.WorkPlaceDTO;
import com.bardouski.servlets.parents.JsonConvertable;

@WebServlet("/WorkPlaceServlet")
public class WorkPlaceServlet extends JsonConvertable {

	private static final long serialVersionUID = 1L;
	private static final String ID = "id";
//	private static final String FULLNAME = "fullname";

	public WorkPlaceServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer userId = Integer.parseInt(request.getParameter(ID));
		PrintWriter printWriter = response.getWriter();

		try {
			WorkPlaceDTO returned = null; //facade. METHOD RETURNED WORKPLACE BY ID
			printWriter.print(prepareJson(returned));
		} catch (NumberFormatException e) {
			printWriter.print(ER_PARAM);
		}
	}

}
