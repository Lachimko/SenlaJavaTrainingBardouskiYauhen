package com.bardouski.ui.actions.parents;

import com.bardouski.config.AutoManagmentPropertiesHolder;
import com.bardouski.program.facade.Facade;

public abstract class FacadePermissionInputAction extends FacadeInputAction{

	protected AutoManagmentPropertiesHolder propertiesHolder;
	protected static final String NO_PERMISSIONS = "No permissions";
	
	public FacadePermissionInputAction(Facade facade, AutoManagmentPropertiesHolder propertiesHolder) {
		super(facade);
		
		this.propertiesHolder = propertiesHolder;
	}
	
}
