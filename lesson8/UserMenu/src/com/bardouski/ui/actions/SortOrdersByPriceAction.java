package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.bardouski.model.IOrder;
import com.bardouski.requests.Request;
import com.bardouski.responses.Response;
import com.bardouski.ui.actions.parents.FacadeAction;

public class SortOrdersByPriceAction extends FacadeAction{

	private static final String EMPTY_COLLECTION = "Empty collection returned.";
	
	public SortOrdersByPriceAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {

		List<IOrder> orders = null;
		Response response;
		
		try {
			out.writeObject(new Request("getAllOrders"));
			
			while ((response = (Response) in.readObject()) != null) {
				
				if ((orders = (List<IOrder>) response.getResponceResult()) != null){
					
					Collections.sort(orders, new Comparator<IOrder>() {

						@Override
						public int compare(IOrder o1, IOrder o2) {
						
							Double o1price = o1.getTask().getPrice();
							Double o2price = o2.getTask().getPrice();

							if (o1price == o2price) {
								return 0;
							} else {
								return (o1price > o2price) ? 1 : -1;
							}
							
						}
					});
					
					printer.print(orders);
				} else {
					printer.print(EMPTY_COLLECTION);
				}
				
				break;
			}
		} catch (IOException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
	}
}