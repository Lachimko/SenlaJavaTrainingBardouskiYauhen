package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.bardouski.model.IOrder;
import com.bardouski.model.impl.enums.OrderStatus;
import com.bardouski.requests.Request;
import com.bardouski.responses.Response;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class CurrentOrdersWithFilterAction extends FacadeInputAction {

	private static final String SORTING_PARAMETERS = "1.Sort by Request Date\n2.Sort by Complete Date\n3.Sort by Price";

	public CurrentOrdersWithFilterAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {

		List<IOrder> list = null;
		try {
			out.writeObject(new Request("getAllOrders", OrderStatus.INPROGRESS));

			Response response;
			while ((response = (Response) in.readObject()) != null) {

				list = (List<IOrder>) response.getResponceResult();
				break;
			}

		} catch (ClassNotFoundException | IOException e) {
			logger.error(e.getMessage(), e);
		}

		printer.print(SORTING_PARAMETERS);
		int sort = scanner.nextInt();

		if (!list.isEmpty()) {

			switch (sort) {
			case 1:
				Collections.sort(list, new Comparator<IOrder>() {

					@Override
					public int compare(IOrder o1, IOrder o2) {
						return (o1.getTask().getRequestDate().before(o1.getTask().getRequestDate())) ? -1 : 1;
					}
				});
				break;
			case 2:
				Collections.sort(list, new Comparator<IOrder>() {

					@Override
					public int compare(IOrder o1, IOrder o2) {
						return (o1.getTask().getCompleteDate().before(o1.getTask().getCompleteDate())) ? -1 : 1;
					}
				});
				break;
			case 3:
				Collections.sort(list, new Comparator<IOrder>() {

					@Override
					public int compare(IOrder o1, IOrder o2) {

						double o1price = o1.getTask().getPrice();
						double o2price = o2.getTask().getPrice();

						if (o1price == o2price) {
							return 0;
						} else {
							return (o1price > o2price) ? 1 : -1;
						}
					}
				});
				break;

			}
		}

		printer.print(list);
	}

}
