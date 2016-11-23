package com.bardouski.ui.actions.parents;

import com.bardouski.program.facade.IFacade;

public abstract class FacadePermissionInputAction extends FacadeInputAction{

	protected boolean permission;
	protected static final String NO_PERMISSIONS = "No permissions";
	
	public FacadePermissionInputAction(IFacade facade, String propertyValue) {
		super(facade);
		
		permission = Boolean.getBoolean(propertyValue);
	}
	
}
