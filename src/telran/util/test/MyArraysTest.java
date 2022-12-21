package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.MyArrays;
import telran.util.comparator.*;

class MyArraysTest {

	@Test
	void sortTest() {
		String[] strings = { "abcd", "lmn", "zz" };
		String[] expected = { "zz", "lmn", "abcd" };
		Integer[] ar = { 3, 2, 1 };

		MyArrays.sort(strings, new StringLengthComparator());
		assertArrayEquals(expected, strings);
		MyArrays.sort(ar, (a, b) -> a - b);

	}

	@Test
	void evenOddTest() {
		Integer numbers[] = { 13, 2, -8, 47, 100, 10, 7 };
		Integer expected[] = { -8, 2, 10, 100, 47, 13, 7 };
		MyArrays.sort(numbers, new EvenOddComparator());
		assertArrayEquals(expected, numbers);
	}

	@Test
	void binarySearchTest() {
		Integer[] numbers = { 1, 2, 3, 5, 6, 7, 8, 9 };
		String[] strings = { "", "1", "23", "456", "7890" };

		assertEquals(MyArrays.binarySearch(numbers, 3, new intComparator()), 2);
		assertEquals(MyArrays.binarySearch(strings, "45", new StringLengthComparator()), 2);

		assertEquals(MyArrays.binarySearch(numbers, 0, new intComparator()), -1);
		assertEquals(MyArrays.binarySearch(strings, "12345", new StringLengthComparator()), -6);

	}

	public <T> void printArray(T[] items) {
		for (T item : items) {
			System.out.print(item + " ");
		}
	}

}