package com.bardouski.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bardouski.model.impl.enums.OrderStatus;
import com.bardouski.servlets.parents.JsonConvertable;

/**
 * Servlet implementation class TicketListServlet
 */
public class TicketListServlet extends JsonConvertable {
	
	private static final long serialVersionUID = 1L;
	private static final String SORTING = "sorting";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sortingparam = request.getParameter(SORTING);

		switch (sortingparam.toLowerCase()) {
		case "all":
			prepareJson(facade.getAllMechanics());
			break;
			
		case "allstatus":
			prepareJson(facade.getAllOrders(OrderStatus.valueOf(sortingparam)));
			break;
	
		case "reqdate":
			prepareJson(facade.sortOrdersByRequestDateAction());
			break;
			
		case "startdate":
			prepareJson(facade.sortOrdersByStartDateAction());
			break;
			
		case "compdate":
			prepareJson(facade.sortOrdersByCompleteDateAction());
			break;	
			
		case "price":
			prepareJson(facade.sortOrdersByPriceAction());
			break;
			
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
