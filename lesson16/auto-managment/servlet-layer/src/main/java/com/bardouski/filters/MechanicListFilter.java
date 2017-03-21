package com.bardouski.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MechanicListFilter implements Filter {

	private static final String SORTING = "sorting";
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
		case GET:

			if (request.getParameter(SORTING) == null) {
				reportError(response, PARAMETER_ERROR);
			} else {
				chain.doFilter(request, response);
			}

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
