package classes.controllers;

public class ArrayProcessor {

	public static String returnSumOfArrayElements(String[] arr) {

		int sum = 0;

		for (int i = 0; i < arr.length; i++) {
			sum = sum + Integer.valueOf(arr[i]);
		}
		return Integer.toString(sum);
	}

	public static String returnLongestStringFromArray(String[] arr) {

		arr = sortArrayAscending(arr);
		return arr[arr.length - 1];
	}

	public static String[] sortArrayAscending(String[] arr) {

		for (int i = 0; i < arr.length; i++) {

			String min = arr[i];
			int min_i = i;

			for (int j = i + 1; j < arr.length; j++) {

				if (arr[j].length() < min.length()) {
					min = arr[j];
					min_i = j;
				}
			}

			if (i != min_i) {
				String tmp = arr[i];
				arr[i] = arr[min_i];
				arr[min_i] = tmp;
			}
		}
		return arr;
	}

	public static String makeStringWithFirstUppercaseLetters(String[] arr) {

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < arr.length; i++) {

			sb.append(makeFirstLetterUppercase(arr[i])).append(" ");
		}
		
		return sb.toString();
	}

	public static String makeFirstLetterUppercase(String word) {

		// Slices first Character, convert to Uppercase, and adds remaining part
		return word.substring(0, 1).toUpperCase() + word.substring(1, word.length());
	}

}
