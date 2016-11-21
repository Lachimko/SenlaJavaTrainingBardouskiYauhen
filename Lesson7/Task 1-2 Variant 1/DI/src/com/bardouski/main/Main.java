package com.bardouski.main;

import java.io.FileNotFoundException;

import com.bardouski.classes.propertiesholder.PropertiesContext;
import com.bardouski.config.AutoManagmentPropertiesHolder;
import com.bardouski.config.AutoManagmentPropertiesLoader;
import com.bardouski.program.controllers.services.IMechanicService;
import com.bardouski.program.controllers.services.IOrderService;
import com.bardouski.program.controllers.services.IWorkPlaceService;
import com.bardouski.program.controllers.stores.IMechanicStore;
import com.bardouski.program.controllers.stores.IOrderStore;
import com.bardouski.program.controllers.stores.IWorkPlaceStore;
import com.bardouski.program.controllers.stores.MechanicStore;
import com.bardouski.program.controllers.stores.OrderStore;
import com.bardouski.program.dbprocessor.DBProcessor;
import com.bardouski.program.dbprocessor.IDBCSVProcessor;
import com.bardouski.program.dbprocessor.IDBProcessor;
import com.bardouski.program.dbprocessor.serializator.ISerializer;
import com.bardouski.program.dbprocessor.serializator.Serializator;
import com.bardouski.program.facade.Facade;
import com.bardouski.program.facade.IFacade;

public class Main {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws FileNotFoundException{
		
		AutoManagmentPropertiesLoader propertiesLoader = new AutoManagmentPropertiesLoader();
		AutoManagmentPropertiesLoader.loadPropertiesPath("resources\\config.properties");
		AutoManagmentPropertiesHolder propertiesHolder = AutoManagmentPropertiesLoader.getProperties();
		
		PropertiesContext context = new PropertiesContext("resources\\context.properties");
		
		IFacade facade = (Facade) context.getInstance("IFacade");
		
		IDBProcessor dbProcessor = (DBProcessor) context.getInstance("IDBProcessor");
		ISerializer serializer = (Serializator) context.getInstance("ISerializer");
		String path = propertiesHolder.getDbPath();
		serializer.setPath(propertiesHolder.getDbPath());
		IDBCSVProcessor dbcsvProcessor = (IDBCSVProcessor) context.getInstance("IDBCSVProcessor");
		dbcsvProcessor.setPath(propertiesHolder.getDbCSVPath());
		dbProcessor.setSerializator(serializer);
		dbProcessor.setDbcsvProcessor(dbcsvProcessor);
		
		IMechanicStore mechanicStore = (MechanicStore) context.getInstance("IMechanicStore");
		IOrderStore orderStore = (OrderStore) context.getInstance("IOrderStore");
		IWorkPlaceStore workPlaceStore = (IWorkPlaceStore) context.getInstance("IWorkPlaceStore");
		
		IMechanicService mechanicService = (IMechanicService) context.getInstance("IMechanicService");
		IOrderService orderService = (IOrderService) context.getInstance("IOrderService");
		IWorkPlaceService workPlaceService = (IWorkPlaceService) context.getInstance("IWorkPlaceService");
		
		facade.setMechanicService(mechanicService);
		facade.setOrderService(orderService);
		facade.setWorkPlaceService(workPlaceService);
		
		
	}

}
