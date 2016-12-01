package com.bardouski.responses;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.bardouski.requests.RequestContainer;

public class ResponseContainer implements Serializable {

	private static final long serialVersionUID = -1245484907866119043L;

	private Object methodsDispather = null;
	private RequestContainer requestContainer = null;
	private Object responceResult;

	public ResponseContainer(RequestContainer requestContainer, Object methodsDispather) {
		this.requestContainer = requestContainer;
		this.methodsDispather = (methodsDispather == null) ? null : methodsDispather;
	}

	public Object getResponceResult() {
		return responceResult;
	}

	public void setResponceResult(Object responceResult) {
		this.responceResult = responceResult;
	}

	public boolean execute() {

		if (requestContainer != null) {

			Class<?> clazz = this.methodsDispather.getClass();
			List<Class<?>> classes = new ArrayList<>();

			for (Object elem : requestContainer.getParams()) {
				classes.add(0, elem.getClass());
			}

			Object[] paramsForMethod = requestContainer.getParams()
					.toArray(new Object[requestContainer.getParams().size()]);
			Class[] classesForMethod = classes.toArray(new Class[classes.size()]);

			try {
				Method method = clazz.getMethod(requestContainer.getMethodName(), classesForMethod);
				responceResult = method.invoke(methodsDispather, paramsForMethod);
				return true;
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				return false;
			}
		}

		return false;
	}

}
