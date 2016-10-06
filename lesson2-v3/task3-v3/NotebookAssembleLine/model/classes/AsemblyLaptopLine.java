package model.classes;

import model.interfaces.IAssamblyLine;
import model.interfaces.ILineStep;
import model.interfaces.IProduct;

public class AsemblyLaptopLine implements IAssamblyLine {

	private ILineStep step1;
	private ILineStep step2;
	private ILineStep step3;

	@Override
	public IProduct assembleProduct(IProduct product) {

		product.installFirstPart(step1.buildProductPart());
		product.installSecondPart(step2.buildProductPart());
		product.installThirdPart(step3.buildProductPart());

		return product;
	}

	public AsemblyLaptopLine(ILineStep step1, ILineStep step2, ILineStep step3) {

		this.step1 = step1;
		this.step2 = step2;
		this.step3 = step3;

		System.out.println(this.getClass().getSimpleName() + " is created.");

	}
}
