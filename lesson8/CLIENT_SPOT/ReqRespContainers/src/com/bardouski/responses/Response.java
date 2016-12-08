package com.bardouski.responses;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.bardouski.requests.Request;

public class Response implements Serializable {

	private static final long serialVersionUID = -1245484907866119043L;

	private transient Object methodsDispather;
	private transient Request request;
	private Object responceResult;

	public Response(Request request, Object methodsDispather) {
		this.request = request;
		this.methodsDispather = (methodsDispather == null) ? null : methodsDispather;
	}

	public Object getResponceResult() {
		return responceResult;
	}

	public void setResponceResult(Object responceResult) {
		this.responceResult = responceResult;
	}

	public void execute() {

		if (request != null) {

			Class<?> clazz = this.methodsDispather.getClass();

			try {
				
				if (!request.getParams().isEmpty()){
					
					List<Class<?>> classes = new ArrayList<>();
					
					for (Object elem : request.getParams()) {
						classes.add(0, elem.getClass());
					}

					Object[] paramsForMethod = request.getParams()
							.toArray(new Object[request.getParams().size()]);
					Class<?>[] classesForMethod = classes.toArray(new Class[classes.size()]);

					try{
						Method method = clazz.getMethod(request.getMethodName(), classesForMethod);
						responceResult = method.invoke(methodsDispather, paramsForMethod);

					} catch (NoSuchMethodException e){

						Method[] methodsOfFacade = clazz.getMethods();

						for (Method method : methodsOfFacade) {
							
							if (method.getName().equals(request.getMethodName())){
								responceResult = method.invoke(methodsDispather, paramsForMethod);
								break;
							}
						}
					}
						
			} else {
			
				Method method = clazz.getMethod(request.getMethodName());
				responceResult = method.invoke(methodsDispather);
			}
			
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				System.err.println("Responce execute throws Exception: " + e);
			}
		}

	}

}
