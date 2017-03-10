package com.bardouski.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class TicketFilter implements Filter{
	
	private static final String DELETE = "delete";
	private static final String NAME = "name";
	private static final String POST = "post";
	private static final String ID = "id";
	private static final String PARAMETER_ERROR = "{parameterError}";
	private static final String GET = "get";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
		HttpServletRequest req = (HttpServletRequest) request;
		String method = req.getMethod();

		switch (method.toLowerCase()) {
		case DELETE:
		case GET:

			try {
				Integer.parseInt(request.getParameter(ID));
				chain.doFilter(request, response);
			} catch (NumberFormatException e) {
				reportError(response, PARAMETER_ERROR);
			}
			break;
		case POST:

			if (request.getParameter(NAME) == null) {
				reportError(response, PARAMETER_ERROR);
			} else {
				chain.doFilter(request, response);
			}
			break;

		default:
			break;
		}
		
	}

	@Override
	public void destroy() {
		
	}
	
	private void reportError(ServletResponse response, String parameterError) throws IOException {
		response.getWriter().println(PARAMETER_ERROR);
	}

}
