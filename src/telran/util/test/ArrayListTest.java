package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.util.ArrayList;

class ArrayListTest {

	@Test
	void test() {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		Integer[] intArray = new Integer[10];
		numbers.add(10);
		numbers.add(1);
		for (int i = 0; i < 10; i++) {
			numbers.add(i*i);
			intArray[i] = i;
		}
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(2);
		printArrayList(numbers);
		printArray(intArray);
		numbers.removeIf(x -> x%2 == 0);
		numbers.toArray(intArray);
		printArrayList(numbers);
		printArray(intArray);
		
//		ArrayList<String> strings = new ArrayList<String>();
//		for (int i = 0; i < 10; i++) {
//			strings.add(String.valueOf(i));
//		}
//		strings.remove("6");
//		printArray(strings);

	}

	public <T> void printArrayList(ArrayList<T> items) {
		for (int i = 0; i < items.size(); i++) {
			System.out.print(items.get(i) + " ");
		}
		System.out.println();
	}
	
	public <T> void printArray(T[] items) {
		for (T item : items) {
			System.out.print(item + " ");
		}
		System.out.println();
	}
}
