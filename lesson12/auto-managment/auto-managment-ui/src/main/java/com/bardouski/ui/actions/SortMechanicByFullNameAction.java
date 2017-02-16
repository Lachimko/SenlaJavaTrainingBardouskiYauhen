package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.bardouski.model.IMechanic;
import com.bardouski.requests.Request;
import com.bardouski.responses.Response;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class SortMechanicByFullNameAction extends FacadeInputAction {

	private static final String EMPTY_COLLECTION = "Empty Collection";

	public SortMechanicByFullNameAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {
		
		List<IMechanic> list = null;
		Response response;
		
		try {
			out.writeObject(new Request("sortMechanicsByFullName"));
			
			while ((response = (Response) in.readObject()) != null) {
				
				if ((list = (List<IMechanic>) response.getResponceResult()) != null){
					printer.print(list);
				} else {
					printer.printFail(EMPTY_COLLECTION);
				}
				
				break;
			}
		} catch (IOException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
}
