package model.classes;

import model.interfaces.IProduct;
import model.interfaces.IProductPart;

public class LaptopProduct implements IProduct {
	
	private IProductPart part1;
	private IProductPart part2;
	private IProductPart part3;

	public IProductPart getPart1() {
		return part1;
	}

	public IProductPart getPart2() {
		return part2;
	}

	public IProductPart getPart3() {
		return part3;
	}

	@Override
	public void installFirstPart(IProductPart part) {
		this.part1 = part;
		System.out.println("first part was installed.");

	}

	@Override
	public void installSecondPart(IProductPart part) {
		this.part2 = part;
		System.out.println("second part was installed.");

	}

	@Override
	public void installThirdPart(IProductPart part) {
		this.part3 = part;
		System.out.println("Third part was installed.");

	}

}
