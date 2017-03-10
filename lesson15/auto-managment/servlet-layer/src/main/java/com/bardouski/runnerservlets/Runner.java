package com.bardouski.runnerservlets;

import java.io.FileNotFoundException;

import com.bardouski.config.ProgramProps;
import com.bardouski.facade.IFacade;
import com.bardouski.propertiesholder.PropertiesContext;

public class Runner {

	private static final String CONFIG = "src//main//resources//config.properties";
	private static final String CONTEXT = "src//main//resources//context.properties";

	@SuppressWarnings("unused")
	public static void main(String[] args) throws FileNotFoundException {

		PropertiesContext context = new PropertiesContext(CONTEXT);
		ProgramProps.loadPropertiesByPath(CONFIG);

		IFacade facade = null;
		
		try {
			facade = (IFacade) PropertiesContext.getInstance(IFacade.class);
			System.out.println("Facade was successfully configured");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
