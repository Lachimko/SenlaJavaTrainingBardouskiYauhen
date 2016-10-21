package controllers;

import model.Dish;
import model.Order;
import model.Table;

public interface IOrderGenerator {

	public Order createOrder(Table table, Dish... dishes);

	public void cancelOrder(int tableNumber);
	
	public void calculateOrder(int tableNumber);
}
