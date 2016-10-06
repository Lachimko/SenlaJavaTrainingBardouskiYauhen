package model.classes.parts;

import model.interfaces.IProductPart;

public class LaptopMotherboard implements IProductPart {

	public LaptopMotherboard() {
		System.out.println(this.getClass().getSimpleName() + " is created.");
	}
}
