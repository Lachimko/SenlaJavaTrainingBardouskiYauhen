package classes.controllers;

import interfaces.INumberGenerator;

public class ThreeRankNumberGenerator implements INumberGenerator {

	@Override
	public int generateRandom(int digitQuantity) {

		int min = (int) Math.pow(10, digitQuantity - 1);
		int max = (int) Math.pow(10, digitQuantity) - 1;

		return min + (int) (Math.random() * ((max - min) + 1));
	}

}
