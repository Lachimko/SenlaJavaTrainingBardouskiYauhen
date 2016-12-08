package com.bardouski.ui.actions.parents;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class FacadePermissionInputAction extends FacadeInputAction{

	protected boolean permission = false;
	protected static final String NO_PERMISSIONS = "No permissions";
	
	public FacadePermissionInputAction(ObjectInputStream in, ObjectOutputStream out, String propertyValue) {

		super(in, out);
		if (propertyValue.equals("true")){
			permission = true;
		}
	}
	
}
