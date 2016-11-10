package com.bardouski.program.main;

import com.bardouski.config.AutoManagmentPropertiesHolder;
import com.bardouski.config.AutoManagmentPropertiesLoader;
import com.bardouski.program.facade.Facade;
import com.bardouski.ui.menuprocessor.*;

public class Main {

	public static void main(String[] args) throws Exception {
		
		AutoManagmentPropertiesLoader propertiesLoader = new AutoManagmentPropertiesLoader(
				"resources\\config.properties");
		
		AutoManagmentPropertiesHolder propertiesHolder = propertiesLoader.getProperties();
		
		Facade facade = new Facade(propertiesHolder.getDbPath());
		MenuBuilder builder = new MenuBuilder(facade, propertiesHolder);
		MenuProcessor menuProcessor = new MenuProcessor(builder.buildMenu());
		menuProcessor.process();
		facade.saveToFile();
		
	}

}
