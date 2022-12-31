package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static telran.util.MyArrays.*;
import telran.util.comparator.*;

class MyArraysTest {

	@Test
	@Disabled
	void sortTest() {
		String[] strings = { "abcd", "lmn", "zz" };
		String[] expected = { "zz", "lmn", "abcd" };
		Integer[] ar = { 3, 2, 1 };

		sort(strings, new StringLengthComparator());
		assertArrayEquals(expected, strings);
		sort(ar, (a, b) -> a - b);
	}

	@Test
	@Disabled
	void evenOddTest() {
		Integer numbers[] = { 13, 2, -8, 47, 100, 10, 7 };
		Integer expected[] = { -8, 2, 10, 100, 47, 13, 7 };
		sort(numbers, new EvenOddComparator());
		assertArrayEquals(expected, numbers);
	}

	@Test
	@Disabled
	void binarySearchTest() {
		Integer[] numbers = { 1, 2, 3, 5, 6, 7, 8, 9 };
		String[] strings = { "", "1", "23", "456", "7890" };
		char[][] array = { { 'a', 'b' }, { 'c', 'd', 'e' } };
		char[] searched = { 'c', 'd' };

		assertEquals(binarySearch(numbers, 3, new intComparator()), 2);
		assertEquals(binarySearch(strings, "45", new StringLengthComparator()), 2);

//		assertEquals(binarySearch(array, new char[] {'2', '3'}, new ArrayLengthComparator<Character>()), 0);
		assertEquals(binarySearch(numbers, 0, new intComparator()), -1);
		assertEquals(binarySearch(strings, "12345", new StringLengthComparator()), -6);

	}

	@Test
	void removeIfTest() {
		Integer[] intArr = { 1, 1, 2, 2, 3, 4, 5, 6, 7, 8 };
		String[] strArr = { "Hello", "World", "I", "Love", "Java", "wORLD" };

		Integer[] intArrExpected = { 1, 1, 3, 5, 7, };
		String[] strArrExpected = { "Hello", "I", "Love", "Java" };
		assertArrayEquals(removeIf(intArr, x -> x % 2 == 0), intArrExpected);
		assertArrayEquals(removeIf(strArr, x -> x.equalsIgnoreCase("world")), strArrExpected);
	}

	@Test
	void containsTest() {
		String[] strArr = { "Hello", "World", "I", "Love", "Java", "wORLD", };
		assertTrue(contains(strArr, "Hello"));
		assertTrue(contains(strArr, "wORLD"));
		assertTrue(contains(new String[] { null, "1", "2" }, null));

		assertFalse(contains(strArr, "1wORLD"));
		assertFalse(contains(strArr, "WORLD"));
		assertFalse(contains(strArr, null));

	}

	@Test
	void removeRepeatedTest() {
		Integer[] intArr = { -1, -1, 0, 0, 1, 1, 2, 2, 3, 3, -1 };
		Integer[] intArrExpected = { -1, 0, 1, 2, 3 };

		String[] strArr = { "Hello", "Hello", "World", "I", "I", "Love", "Java", "wORLD", "wORLD" };
		String[] strArrExpected = { "Hello", "World", "I", "Love", "Java", "wORLD" };

		assertArrayEquals(removeRepeated(intArr), intArrExpected);
		assertArrayEquals(removeRepeated(strArr), strArrExpected);
	}

	public <T> void printArray(T[] items) {
		for (T item : items) {
			System.out.print(item + " ");
		}
		System.out.println();
	}

}