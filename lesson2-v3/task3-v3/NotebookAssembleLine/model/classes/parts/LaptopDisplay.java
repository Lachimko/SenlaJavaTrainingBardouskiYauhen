package model.classes.parts;

import model.interfaces.IProductPart;

public class LaptopDisplay implements IProductPart {

	public LaptopDisplay() {
		
		System.out.println(this.getClass().getSimpleName() + " is created.");
	}
}
