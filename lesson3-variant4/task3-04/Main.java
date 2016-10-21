import controllers.MenuProcessor;
import controllers.OrderGenerator;
import controllers.PersonalController;
import model.Cook;
import model.Dish;
import model.Menu;
import model.Order;
import model.Table;

public class Main {

	public static void main(String[] args) {

		Menu menu = new Menu();
		MenuProcessor menuProcessor = new MenuProcessor(menu);

		// add / remove dishes from menu
		menuProcessor.addDishes(new Dish("Ceasar", 12.99), new Dish("Winter", 6), new Dish("Spring", 15.55),
				new Dish("Olivier", 4.52));
		menuProcessor.removeDishes("Ceasar");

		// add / remove cook
		PersonalController personalController = new PersonalController();
		personalController.addCook(new Cook("Aleksey"), new Cook("Dmitriy"), new Cook("Anton"));
		personalController.viewCooksStatus();
		// personalController.removeCook("Dmitriy");

		// add / remove order from array by table name
		Table table1 = new Table(1);
		Table table2 = new Table(2);

		OrderGenerator orderGenerator = new OrderGenerator(menu, personalController);
		// search free cook before order creates
		Order order1 = orderGenerator.createOrder(table1, menu.getDishes()[0], menu.getDishes()[1],
				menu.getDishes()[2]);
		Order order2 = orderGenerator.createOrder(table2, menu.getDishes()[0], menu.getDishes()[1],
				menu.getDishes()[2]);

		// Get price from tables
		orderGenerator.calculateOrder(2);

		// View what cooks do rigth now
		personalController.viewCooksStatus();
		orderGenerator.cancelOrder(1);
		orderGenerator.viewOrders();

		// menu view
		menuProcessor.viewMenu();
	}

}
