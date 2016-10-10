package model;

public class Dish {
	
	private String title;
	private double price;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Dish(String title, double price) {
		this.title = title;
		this.price = price;
	}

}
