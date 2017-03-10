package com.bardouski.ui.main;

import com.bardouski.config.ProgramProps;
import com.bardouski.program.facade.Facade;
import com.bardouski.program.facade.IFacade;
import com.bardouski.propertiesholder.PropertiesContext;
import com.bardouski.ui.menuprocessor.IMenuBuilder;
import com.bardouski.ui.menuprocessor.IMenuProcessor;
import com.bardouski.ui.menuprocessor.MenuBuilder;
import com.bardouski.ui.menuprocessor.MenuProcessor;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		
		ProgramProps.loadPropertiesByPath("resources\\config.properties");
		PropertiesContext context = new PropertiesContext("resources\\context.properties");
		
		IFacade facade = (Facade) PropertiesContext.getInstance(IFacade.class);
		
		IMenuBuilder builder = (MenuBuilder) PropertiesContext.getInstance(IMenuBuilder.class);
		builder.setFacade(facade);
		
		MenuProcessor menuProcessor = (MenuProcessor) PropertiesContext.getInstance(IMenuProcessor.class);
		menuProcessor.setRoot(builder.buildMenu());
		
		menuProcessor.process();
		
	}

}
