package com.bardouski.servlets.parents;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JsonConvertable extends FacadeReferenceable {

	protected static final String NULL = "{null}";
	private static final long serialVersionUID = 1L;
	protected static ObjectMapper mapper = null;
	
	public JsonConvertable() {
		super();

		if (JsonConvertable.mapper == null) {
			mapper = new ObjectMapper();
		}
	}
	
	protected String prepareJson(Object toWrite) {
		try {
			return mapper.writeValueAsString(toWrite);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return NULL;
		}
	}
	
}
