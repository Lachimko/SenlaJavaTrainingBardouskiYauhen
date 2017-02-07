package com.bardouski.ui.actions;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.bardouski.model.impl.Ticket;
import com.bardouski.model.impl.enums.OrderStatus;
import com.bardouski.requests.Request;
import com.bardouski.responses.Response;
import com.bardouski.ui.actions.parents.FacadeInputAction;

public class AdvancedOrdersOperationsAction extends FacadeInputAction {

	private static final String NO_RESULT = "No result";
	private static final String PARSE_ERROR = "Wrong Date Pattern";
	private static final String TO_DATE = "To Date (dd.MM.yyyy):";
	private static final String FROM_DATE = "From Date (dd.MM.yyyy):";
	private static final String ORDERS_PICKER = "1.Take Ready Orders\n2.Take Deleted Orders\n3.Take Cancelled Orders";
	private static final String SORTING_PARAMETERS = "1.Sort by Request Date\n2.Sort by Complete Date\n3.Sort by Price";

	public AdvancedOrdersOperationsAction(ObjectInputStream in, ObjectOutputStream out) {
		super(in, out);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void execute() {

		List<Ticket> list = new ArrayList<>();
		int chosen;
		printer.print(ORDERS_PICKER);
		chosen = scanner.nextInt();

		try {
			switch (chosen) {
			case 1:
				out.writeObject(new Request("getAllOrders", OrderStatus.READY));
				break;
			case 2:
				out.writeObject(new Request("getAllOrders", OrderStatus.DELETED));
				break;
			case 3:
				out.writeObject(new Request("getAllOrders", OrderStatus.CANCELLED));
				break;
			}

			Response response;

			while ((response = (Response) in.readObject()) != null) {
				list = (List<Ticket>) response.getResponceResult();
				break;
			}

		} catch (IOException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
			printer.printFail(e.getMessage());
		}

		if (!list.isEmpty()) {
			try {
				printer.print(FROM_DATE);
				Date start = scanner.parseDate(scanner.nextLine());
				printer.print(TO_DATE);
				Date end = scanner.parseDate(scanner.nextLine());

				List<Ticket> temp = new ArrayList<>();
				for (Ticket order : list) {
					if (order.getTask().getRequestDate().after(start) && order.getTask().getRequestDate().before(end)) {
						temp.add(order);
					}
				}
				list = temp;
			} catch (ParseException e) {
				logger = Logger.getLogger(this.getClass().getSimpleName());
				logger.error(PARSE_ERROR);
				printer.printFail(PARSE_ERROR);
			}
		}

		if (!list.isEmpty()) {
			printer.print(SORTING_PARAMETERS);
			chosen = scanner.nextInt();

			switch (chosen) {
			case 1:
				Collections.sort(list, new Comparator<Ticket>() {

					@Override
					public int compare(Ticket o1, Ticket o2) {
						return (o1.getTask().getRequestDate().before(o1.getTask().getRequestDate())) ? -1 : 1;
					}

				});
				break;
			case 2:
				Collections.sort(list, new Comparator<Ticket>() {

					@Override
					public int compare(Ticket o1, Ticket o2) {
						return (o1.getTask().getCompleteDate().before(o1.getTask().getCompleteDate())) ? -1 : 1;
					}

				});
				break;
			case 3:
				Collections.sort(list, new Comparator<Ticket>() {

					@Override
					public int compare(Ticket o1, Ticket o2) {

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

			printer.print(list);

		} else {
			printer.print(NO_RESULT);
		}
	}

}
