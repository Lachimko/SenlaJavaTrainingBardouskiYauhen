package model.classes.partinstallers;

import model.classes.parts.LaptopMotherboard;
import model.interfaces.ILineStep;
import model.interfaces.IProductPart;

public class MotherboardInstaller implements ILineStep {

	@Override
	public IProductPart buildProductPart() {

		System.out.println(this.getClass().getSimpleName() + " done any work.");
		return new LaptopMotherboard();
	}

}
