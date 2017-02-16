package com.bardouski.requests;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Container for holding transfered methodName and parameters by ObjectStreams
 * Filling RequestContainer fields with constructor @see
 * RequestContainer#RequestContainer(String, Object...) or @see
 * RequestContainer#pushMethodNameAndParameters(String, Object...)
 */
public class Request implements Serializable {

	private static final long serialVersionUID = -6593910811312869349L;

	private String methodName;
	private List<Object> params = new ArrayList<>();

	public Request() {
	}

	/**
	 * @param methodName
	 *            - name of method
	 * @param params
	 *            - parameters pushed to method
	 */
	public Request(String methodName, Object... params) {

		this.methodName = methodName;

		for (Object param : params) {
			this.params.add(param);
		}
	}

	public List<Object> getParams() {
		return params;
	}

	public String getMethodName() {
		return methodName;
	}

}