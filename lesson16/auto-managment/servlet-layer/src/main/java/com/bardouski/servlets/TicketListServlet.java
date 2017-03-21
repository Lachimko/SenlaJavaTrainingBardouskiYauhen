package com.bardouski.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bardouski.model.impl.enums.OrderStatus;
import com.bardouski.servlets.parents.JsonConvertable;

@WebServlet(urlPatterns="/TicketListServlet")
public class TicketListServlet extends JsonConvertable {

	private static final long serialVersionUID = 1L;

	private static final String SORTING = "sorting";
	private static final String ALL_KEYWORD = "";
	private static final String COMPLETE_DATE = "completeDate";
	private static final String START_DATE = "startDate";
	private static final String ORDER_STATUS = "orderStatus";
	private static final String PRICE = "price";
	private static final String REQUEST_DATE = "requestDate";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sortingParam = request.getParameter(SORTING);
		PrintWriter writer = response.getWriter();

		if (sortingParam.equals(ORDER_STATUS)) {
			writer.print(prepareJson(facade.getAllOrders(OrderStatus.valueOf(sortingParam))));
		} else if (sortingParam.equals(ALL_KEYWORD) || sortingParam.equals(PRICE) || sortingParam.equals(REQUEST_DATE)
				|| sortingParam.equals(START_DATE) || sortingParam.equals(COMPLETE_DATE)) {
			
			writer.print(prepareJson(facade.getAllOrders(sortingParam)));
		} else {
			writer.print(ER_PARAM);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
