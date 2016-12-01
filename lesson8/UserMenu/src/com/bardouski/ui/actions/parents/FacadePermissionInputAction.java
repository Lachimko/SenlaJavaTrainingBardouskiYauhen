package com.bardouski.ui.actions.parents;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class FacadePermissionInputAction extends FacadeInputAction{

	protected boolean permission;
	protected static final String NO_PERMISSIONS = "No permissions";
	
	public FacadePermissionInputAction(ObjectInputStream in, ObjectOutputStream out, String propertyValue) {

		super(in, out);
		permission = Boolean.getBoolean(propertyValue);
	}
	
}
