package telran.recursion.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.recursion.MdArray;

class MdArrayTest {
	MdArray<Integer> numbers;
	int[] intDimensions = { 10, 5, 7 };
	int intValue = 50;
	MdArray<String> strings;
	int[] strDimensions = { 3, 15, 7, 2, 10 };
	String strValue = "hello";
	Integer[] empty = {};

	Integer[] ar = new Integer[Arrays.stream(intDimensions).reduce((a, b) -> a * b).getAsInt() + 100];

	@BeforeEach
	void setUp() {
		numbers = new MdArray<>(intDimensions, intValue);
		strings = new MdArray<>(strDimensions, strValue);
	}

	@Test
	void forEachTest() {
		Integer[] sum = { 0 };
		numbers.forEach(num -> sum[0] += num);
		assertEquals(Arrays.stream(intDimensions).reduce((a, b) -> a * b).getAsInt() * intValue, sum[0]);
	}

	@Test
	void toArrayTest() {
		Integer[] numberArray = new Integer[Arrays.stream(intDimensions).reduce((a, b) -> a * b).getAsInt()];
		Arrays.fill(numberArray, intValue);
		assertArrayEquals(numbers.toArray(empty), numberArray);

		Arrays.fill(ar, 10);
		assertTrue(ar == numbers.toArray(ar));
		for (int i = 0; i < numberArray.length; i++) {
			assertEquals(ar[i], numberArray[i]);
		}
		for (int i = numberArray.length; i < ar.length; i++) {
			assertNull(ar[i]);
		}
	}

	@Test
	void getValueTest() {
		for (int i = 0; i < intDimensions[0]; i++) {
			for (int j = 0; j < intDimensions[1]; j++) {
				for (int k = 0; k < intDimensions[2]; k++) {
					assertEquals(numbers.getValue(new int[] { i, j, k }), intValue);
				}
			}
		}
		assertEquals(strings.getValue(new int[] { 2, 5, 3, 1, 3 }), strValue);
		
		assertThrowsExactly(IllegalStateException.class, () -> numbers.getValue(new int[] { 3, 4 }));
		assertThrowsExactly(NoSuchElementException.class, () -> numbers.getValue(new int[] { 3, 4, 6, 0 }));
		assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> numbers.getValue(new int[] { 3, 4, 7 }));
	}

	@Test
	void setValueTest() {
		numbers.setValue(new int[] { 3, 4, 6 }, 100);
		assertEquals(numbers.getValue(new int[] { 3, 4, 6 }), 100);
		assertThrowsExactly(IllegalStateException.class, () -> numbers.setValue(new int[] { 3, 4 }, 100));
		assertThrowsExactly(NoSuchElementException.class, () -> numbers.setValue(new int[] { 3, 4, 6, 0 }, 100));
		assertThrowsExactly(ArrayIndexOutOfBoundsException.class, () -> numbers.setValue(new int[] { 3, 4, 7 }, 100));
	}
}
