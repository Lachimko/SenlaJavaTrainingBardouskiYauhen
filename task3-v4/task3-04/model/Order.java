package model;

public class Order {

	private Table table;
	private Dish[] dishes;
	private Cook cook;

	public Order(Table table, Cook cook, Dish... dishes) {
		this.cook = cook;
		this.table = table;
		this.dishes = dishes;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Dish[] getDishes() {
		return dishes;
	}

	public void setDishes(Dish[] dishes) {
		this.dishes = dishes;
	}

	public Cook getCook() {
		return cook;
	}

	public void setCook(Cook cook) {
		this.cook = cook;
	}
	
	@Override
	public String toString() {
		
		String s = "Table №: " + table.getNumber() + ", Cook: " + cook.getName() + ", Dishes: " + dishes;
		return s;
	}

}
