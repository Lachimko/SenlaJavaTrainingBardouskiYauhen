package com.bardouski.runnerservlets;

import java.io.FileNotFoundException;
import java.util.List;

import com.bardouski.config.ProgramProps;
import com.bardouski.facade.IFacade;
import com.bardouski.model.impl.dto.MechanicDTO;
import com.bardouski.program.facade.Facade;
import com.bardouski.propertiesholder.PropertiesContext;

public class Runner {

	private static final String CONFIG = "config.properties";
	private static final String CONTEXT = "context.properties";

//	private static final String WORK_KEYWORD = "currentOrder";
//	private static final String ALL_KEYWORD = "";
	
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {

		Facade facade = null;

		try {

			new PropertiesContext(CONTEXT);
			ProgramProps programProps = (ProgramProps) PropertiesContext.getInstance(ProgramProps.class);
			programProps.configure(CONFIG);

			facade = (Facade) PropertiesContext.getInstance(IFacade.class);

			List<MechanicDTO> list = facade.getAllMechanics("currentOrder");
			for (MechanicDTO mechanicDTO : list) {
				System.out.println(mechanicDTO);
			}
			
		} catch (ClassNotFoundException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
