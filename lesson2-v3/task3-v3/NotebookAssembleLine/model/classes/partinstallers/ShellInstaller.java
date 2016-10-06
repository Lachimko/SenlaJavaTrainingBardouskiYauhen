package model.classes.partinstallers;

import model.classes.parts.LaptopShell;
import model.interfaces.ILineStep;
import model.interfaces.IProductPart;

public class ShellInstaller implements ILineStep {

	@Override
	public IProductPart buildProductPart() {

		System.out.println(this.getClass().getSimpleName() + " done any work.");
		return new LaptopShell();
	}

}
