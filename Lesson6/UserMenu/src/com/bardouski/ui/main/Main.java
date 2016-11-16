package com.bardouski.ui.main;

import com.bardouski.config.AutoManagmentPropertiesHolder;
import com.bardouski.config.AutoManagmentPropertiesLoader;
import com.bardouski.program.facade.Facade;
import com.bardouski.ui.menuprocessor.MenuBuilder;
import com.bardouski.ui.menuprocessor.MenuProcessor;

public class Main {

	public static void main(String[] args) throws Exception {
		
		AutoManagmentPropertiesLoader.loadPropertiesPath("resources\\config.properties");
		AutoManagmentPropertiesHolder propertiesHolder = AutoManagmentPropertiesLoader.getProperties();
		
		Facade facade = new Facade(propertiesHolder.getDbPath(), propertiesHolder.getDbCSVPath());
		MenuBuilder builder = new MenuBuilder(facade, propertiesHolder);
		MenuProcessor menuProcessor = new MenuProcessor(builder.buildMenu());
		menuProcessor.process();
		
	}

}
