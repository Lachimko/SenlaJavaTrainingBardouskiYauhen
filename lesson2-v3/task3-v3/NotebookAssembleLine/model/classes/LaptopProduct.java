package model.classes;

import model.interfaces.IProduct;
import model.interfaces.IProductPart;

public class LaptopProduct implements IProduct {

	@Override
	public void installFirstPart(IProductPart part) {
		System.out.println("first part was installed.");

	}

	@Override
	public void installSecondPart(IProductPart part) {
		System.out.println("second part was installed.");

	}

	@Override
	public void installThirdPart(IProductPart part) {
		System.out.println("Third part was installed.");

	}

}
