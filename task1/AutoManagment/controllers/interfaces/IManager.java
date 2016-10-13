package controllers.interfaces;

public interface IManager<T> {
	
	void add(T unit);
	
	void remove(T unit);
	
	void viewAll();

}
