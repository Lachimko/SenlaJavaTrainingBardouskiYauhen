package model.classes.parts;

import model.interfaces.IProductPart;

public class LaptopShell implements IProductPart {

	public LaptopShell() {
		System.out.println(this.getClass().getSimpleName() + " is created.");
	}
}
