package controllers;

import model.Dish;
import model.Menu;

public class MenuProcessor {

	private Menu menu;

	public MenuProcessor(Menu menu) {
		this.menu = menu;
	}

	public void addDishes(Dish... dishes) {

		if (menu.getDishes() != null) {

			Dish[] newDishesArray = new Dish[menu.getDishes().length + dishes.length];

			System.arraycopy(menu.getDishes(), 0, newDishesArray, 0, menu.getDishes().length);
			System.arraycopy(dishes, 0, newDishesArray, menu.getDishes().length, dishes.length);
			menu.setDishes(newDishesArray);

		} else
			menu.setDishes(dishes);
	}

	public void viewMenu() {

		System.out.println("MENU_______________________________");

		for (Dish dish : menu.getDishes()) {
			System.out.printf("%s ~ price: %.02f \n", dish.getTitle(), dish.getPrice());
		}

		System.out.println("___________________________________");
	}

	public void removeDishes(String dishName) {

		try {
			
			//Checks if elements exist in menu 
			int menuLength = menu.getDishes().length;
			Dish[] temp = new Dish[menuLength - 1];
			
			for (int i = 0, j = 0; i < menuLength; i++, j++) {
				
				if (!menu.getDishes()[i].getTitle().equals(dishName)){
					
					temp[j] = menu.getDishes()[i];
				} else j--;
			}
			
			menu.setDishes(temp);
			
		} catch (NullPointerException e) {
			System.out.println("Something gonna bad: menu is empty ....");
		}

	}
}
