import classes.controllers.StringArrayGenerator;
import static classes.controllers.ArrayProcessor.*;

public class Main {

	public static void main(String[] args) {

		StringBuffer sb = new StringBuffer();
		StringArrayGenerator sag = new StringArrayGenerator();

		// Task1-1
		final int CAPACITY_OF_ARRAY = 10;
		String task1_01Result = returnSumOfArrayElements(sag.generateArrayOfStringsFromInts(CAPACITY_OF_ARRAY));
		System.out.println("Task 1_01: Summ of array elements: " + task1_01Result);

		// Task1-2
		String[] task2Array = sag.generateArrayOfStrings();
		System.out.print("Task 1_02: The longest element in array is: ");
		System.out.println(returnLongestStringFromArray(task2Array));

		// Task1-3
		String TASK1_3_WORD = "   Copies an array from the specified source array ";
		String[] s = TASK1_3_WORD.trim().split(" ");
		System.out.print("Task 1_03: Splited array: ");

		for (int i = 0; i < s.length; i++) {
			sb.append(s[i]).append(", ");
		}
		System.out.println(sb);
		sb.delete(0, sb.length());

		// Task1-4
		String[] task4Array = sag.generateArrayOfStrings();
		String task1_04Result = makeStringWithFirstUppercaseLetters(task4Array);
		System.out.print("Task 1_04: Uppercased array: ");
		System.out.println(task1_04Result);
	}

}
