package classes.controllers;

import java.util.Random;

import interfaces.IArrayGenerator;

public class StringArrayGenerator implements IArrayGenerator {

	// Task 1-01 Method
	@Override
	public String[] generateArrayOfStringsFromInts(int capacity) {

		final int INTEGER_MAX_OF_RANDOM = 100;
		String[] arr = new String[capacity];
		Random rnd = new Random();

		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.toString(rnd.nextInt(INTEGER_MAX_OF_RANDOM));
		}

		return arr;
	}

	// Task 1-02 Method
	@Override
	public String[] generateArrayOfStrings() {

		return new String[] { "васька", "кузя", "барсик", "мурзик", "леопольд", "бегемот", "рыжик", "матроскин" };
	}

}
