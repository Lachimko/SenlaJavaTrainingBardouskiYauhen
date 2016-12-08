package com.bardouski.responses;

import java.io.Serializable;

public class Response implements Serializable {

	private static final long serialVersionUID = -1245484907866119043L;

	private Object responceResult;

	public Response(Object responceResult){
		this.responceResult = responceResult;
	}
	
	public Object getResponceResult() {
		return responceResult;
	}

	public void setResponceResult(Object responceResult) {
		this.responceResult = responceResult;
	}

	
}
