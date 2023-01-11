package telran.util.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;

import telran.util.*;

public abstract class ListTest extends CollectionTest {
	List<Integer> list;

	@BeforeEach
	void setUp() throws Exception {
		super.setUp();
		list = (List<Integer>) collection;
	}

	@Test
	@Override
	void testAdd() {
		assertTrue(list.add(numbers[0]));
		assertEquals(numbers.length + 1, list.size());
	}

	@Test

	void testAddInt() {
		Integer[] expected1 = { 10, 100, -5, 100, 134, 280, 120, 15 };
		Integer[] expected2 = { 8, 10, 100, -5, 100, 134, 280, 120, 15 };
		Integer[] expected3 = { 8, 10, 100, -5, 100, 134, 280, 120, 15, 200 };
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.add(1000, 100));
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.add(-1, 100));
		list.add(3, 100);
		assertArrayEquals(expected1, list.toArray(empty));
		list.add(0, 8);
		assertArrayEquals(expected2, list.toArray(empty));
		list.add(list.size(), 200);
		assertArrayEquals(expected3, list.toArray(empty));
	}

	@Test
	void testRemoveInt() {
		Integer[] expected1 = { 10, 100, -5, 280, 120, 15 };
		Integer[] expected2 = { 100, -5, 280, 120, 15 };
		Integer[] expected3 = { 100, -5, 280, 120 };
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.remove(1000));
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.remove(-1));
		assertEquals(134, list.remove(3));
		assertArrayEquals(expected1, list.toArray(empty));
		assertEquals(10, list.remove(0));
		assertArrayEquals(expected2, list.toArray(empty));
		assertEquals(15, list.remove(list.size() - 1));
		assertArrayEquals(expected3, list.toArray(empty));
	}

	@Test
	void testIndexOf() {
		for (int i = 0; i < numbers.length; i++) {
			assertEquals(i, list.indexOf(numbers[i]));

		}
		assertEquals(-1, list.indexOf(Integer.MAX_VALUE));
	}

	@Test
	void testLastIndexOf() {
		list.add(3, 134);
		assertEquals(3, list.indexOf(134));
		assertEquals(4, list.lastIndexOf(134));
		assertEquals(-1, list.lastIndexOf(Integer.MAX_VALUE));

	}

	@Test
	void testGet() {
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.get(1000));
		assertEquals(10, list.get(0));
	}

	@Test
	void testSet() {
		list.set(0, 1000);
		assertEquals(1000, list.get(0));
	}

	@Test
	@Override
	void testIterator() {
		Integer[] actual = new Integer[numbers.length];
		int index = 0;
		Iterator<Integer> it = list.iterator();
		while (it.hasNext()) {
			actual[index++] = it.next();
		}
		assertArrayEquals(numbers, actual);
		assertThrowsExactly(NoSuchElementException.class, () -> it.next());
	}

}