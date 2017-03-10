package com.bardouski.program.controllers.stores.dao;

import com.bardouski.controllers.dao.AbstractDAO;
import com.bardouski.controllers.dao.ITaskDAO;
import com.bardouski.model.impl.Task;

public class TaskDAO extends AbstractDAO<Task> implements ITaskDAO {

	public TaskDAO() {
		super(Task.class);
	}

}
