package model.classes.partinstallers;

import model.classes.parts.LaptopDisplay;
import model.interfaces.ILineStep;
import model.interfaces.IProductPart;

public class DisplayInstaller implements ILineStep {

	@Override
	public IProductPart buildProductPart() {

		System.out.println(this.getClass().getSimpleName() + " done any work.");
		return new LaptopDisplay();
	}

}
