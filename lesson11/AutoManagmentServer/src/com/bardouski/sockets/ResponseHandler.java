package com.bardouski.sockets;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.bardouski.facade.IFacade;
import com.bardouski.requests.Request;
import com.bardouski.responses.Response;

public class ResponseHandler {

	private IFacade facade;
	
	public ResponseHandler(IFacade facade) {
		this.facade = facade;
	}

	public Response executeAndReturnResponce(Request request) {
		
		return execute(request);
	}
	
	private Response execute(Request request) {

		if (request != null) {

			Class<?> clazz = facade.getClass();
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
						return new Response(method.invoke(facade, paramsForMethod));

					} catch (NoSuchMethodException e){

						Method[] methodsOfFacade = clazz.getMethods();

						for (Method method : methodsOfFacade) {
							
							if (method.getName().equals(request.getMethodName())){
								return new Response(method.invoke(facade, paramsForMethod));
							}
						}
					}
						
			} else {
			
				Method method = clazz.getMethod(request.getMethodName());
				return new Response(method.invoke(facade));
			}
			
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				System.err.println("Responce execute throws Exception: " + e);
			}
		} 
		
		return null;
	}


}
