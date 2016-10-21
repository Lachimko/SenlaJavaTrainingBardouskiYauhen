package controllers;

import model.Cook;
import model.Dish;
import model.Menu;
import model.Order;
import model.Table;

public class OrderGenerator implements IOrderGenerator {

	private Menu menu;
	private PersonalController pc;
	private Order[] orders;

	private void setOrders(Order[] orders) {
		this.orders = orders;
	}

	public OrderGenerator(Menu menu, PersonalController pc) {
		this.menu = menu;
		this.pc = pc;
	}

	@Override
	public Order createOrder(Table table, Dish... dishes) {

		Cook cook = findCook();
		if (cook != null) {

			Order order = new Order(table, cook, dishes);
			addToList(order);
			return order;
		} else {

			System.out.println("Sorry, no free cooks are aviable now...");
			return null;
		}
	}

	private void addToList(Order order) {

		if (orders != null) {

			Order[] ord = new Order[this.orders.length + 1];

			System.arraycopy(orders, 0, ord, 1, orders.length);
			ord[0] = order;
			this.orders = ord;

		} else {
			Order[] ord = new Order[1];
			ord[0] = order;
			this.orders = ord;
		}

		order.getCook().setCurrentWork(order);

	}

	private Cook findCook() {

		for (int i = 0; i < pc.getCooks().length; i++) {

			Cook current = pc.getCooks()[i];

			if (current.getCurrentWork() == null) {
				return current;
			}
		}

		return null;

	}

	@Override
	public void cancelOrder(int tableNumber) {

		try {
			// Checks if elements exist in menu
			int ordersLength = this.orders.length;
			Order[] temp = new Order[ordersLength - 1];

			for (int i = 0, j = 0; i < ordersLength; i++, j++) {

				if (this.orders[i].getTable().getNumber() != tableNumber) {

					temp[j] = orders[i];
				} else
					j--;
			}

			setOrders(temp);

		} catch (NullPointerException e) {
			System.out.println("Something gonna bad: No table in Orders....");
		}

	}

	public void viewOrders() {

		if (this.orders != null) {

			StringBuffer sb = new StringBuffer();

			for (Order order : this.orders) {
				sb.append("***ORDER**** \n").append("Table: ").append(order.getTable().getNumber()).append("\n");
				sb.append("Cook: ").append(order.getCook().getName()).append("\n");
				sb.append("Dishes: ");
				for (Dish dish : order.getDishes()) {

					sb.append(dish.getTitle()).append(", ");
				}
				sb.append("\n");
			}
			System.out.println(sb);
		} else
			System.out.println("No orders");
	}

	@Override
	public void calculateOrder(int tableNumber) {

		StringBuffer sb = new StringBuffer();
		double sum = 0;
		sb.append("Order from table: ").append(tableNumber).append("\n");

		for (Order order : orders) {

			if (order.getTable().getNumber() == tableNumber) {

				for (Dish dish : order.getDishes()) {
					double price = dish.getPrice();
					sum += price;
					sb.append(dish.getTitle()).append(" = ").append(price).append("\n");
				}
			}
		}
		sb.append("___total____= ").append(sum).append(" $\n");
		System.out.println(sb);
	}

}
