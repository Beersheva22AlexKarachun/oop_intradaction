package telran.recursion.test;

import static org.junit.jupiter.api.Assertions.*;
import static telran.recursion.LinearRecursion.*;

import org.junit.jupiter.api.Test;

public class LinearRecursionTest {
	int[] numbers = { 3, 5, -7, 11, 13, -17, 19, -23 };
	int[] powers = { 7, 6, 5, 4, 3, 2, 1, 4 };

	@Test
	void fTest() {
		f(6);
	}

	void f(int a) {
		if (a > 5) {
			f(a - 1);
		}
	}

	@Test
	void factorialTest() {
		assertEquals(24, factorial(4));
		assertEquals(24 * 5 * 6, factorial(6));
		assertEquals(24, factorial(-4));
	}

	@Test
	void powerTest() {
		for (int i = 0; i < numbers.length; i++) {
			int number = numbers[i], power = powers[i];
			assertEquals(Math.pow(number, power), power(number, power));
		}

		assertEquals(1, power(1000, 0));
		assertThrowsExactly(IllegalArgumentException.class, () -> power(1000, -1));
		assertEquals(1000, power(10, 3));
		assertEquals(-1000, power(-10, 3));
		assertEquals(256, power(-2, 8));
		assertEquals(-128, power(-2, 7));
	}

	@Test
	void sumTest() {
		int ar[] = { 1, 2, 3, 4, 5, 6 };
		assertEquals(21, sum(ar));
		assertEquals(0, sum(new int[0]));
	}

	@Test
	void reverseTest() {
		int ar[] = { 1, 2, 3, 4, 5, 6 };
		int expected[] = { 6, 5, 4, 3, 2, 1 };
		int ar1[] = { 1, 2, 3, 4, 5, 6, 7 };
		int expected1[] = { 7, 6, 5, 4, 3, 2, 1 };
		reverse(ar);
		reverse(ar1);
		assertArrayEquals(expected, ar);
		assertArrayEquals(expected1, ar1);

	}

	@Test
	void squareTest() {
		assertEquals(0, square(0));
		assertEquals(1, square(1));
		assertEquals(4, square(2));
		assertEquals(9, square(3));
		assertEquals(16, square(4));
		assertEquals(25, square(5));
		assertEquals(36, square(-6));
		assertEquals(49, square(-7));
	}

	@Test
	void subStringTest() {
		String str = "qwerty123qwe4";
		assertTrue(isSubstring(str, ""));
		assertTrue(isSubstring(str, "ty1"));
		assertTrue(isSubstring(str, "q"));
		assertTrue(isSubstring(str, "qwerty123qwe4"));
		assertTrue(isSubstring(str, "qwe4"));
		assertTrue(isSubstring("", ""));
		assertFalse(isSubstring(str, "234"));
		assertFalse(isSubstring(str, "876"));
		assertFalse(isSubstring(str, "qwert1234"));
		assertFalse(isSubstring(str, "qwer1"));
		assertFalse(isSubstring(str, "qwe3"));
		assertFalse(isSubstring("", "q"));
	}
}