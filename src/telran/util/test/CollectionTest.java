package telran.util.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

public abstract class CollectionTest {
	protected Integer[] numbers = { 10, 100, -5, 134, 280, 120, 15 };
	protected Integer[] ar = new Integer[numbers.length + 100];
	protected Integer[] empty = {};
	protected Collection<Integer> collection;

	@BeforeEach
	void setUp() throws Exception {
		for (Integer num : numbers) {
			collection.add(num);
		}
	}

	abstract void testAdd();

	abstract void testIterator();

	@SuppressWarnings("unchecked")
	@Test
	void testConstructorCopy() throws Exception {
		Collection<Integer> copiedCollection = collection.getClass().getConstructor(Collection.class)
				.newInstance(collection);
		assertArrayEquals(collection.toArray(empty), copiedCollection.toArray(empty));
	}

	@Test
	void testRemove() {
		Integer[] expected = { 10, 100, -5, 280, 120, 15 };
		assertTrue(collection.remove((Integer) 134));
		assertArrayEquals(expected, collection.toArray(empty));
		assertFalse(collection.remove((Integer) 134));
	}

	@Test
	void testRemoveIf() {
		Integer[] expected = { -5, 15 };
		assertTrue(collection.removeIf(n -> n % 2 == 0));
		assertArrayEquals(expected, collection.toArray(empty));
		assertFalse(collection.removeIf(n -> n % 2 == 0));
		assertTrue(collection.removeIf(n -> true));
		assertTrue(collection.isEmpty());

	}

	@Test
	void testIsEmpty() {
		assertFalse(collection.isEmpty());
		collection.removeIf(n -> true);
		assertTrue(collection.isEmpty());
	}

	@Test
	void testSize() {
		assertEquals(numbers.length, collection.size());
	}

	@Test
	void testContains() {
		assertTrue(collection.contains(numbers[0]));
		assertFalse(collection.contains(Integer.MIN_VALUE));
	}

	@Test
	void testToArray() {
		Arrays.fill(ar, 10);
		assertTrue(ar == collection.toArray(ar));
		for (int i = 0; i < numbers.length; i++) {
			assertEquals(ar[i], numbers[i]);
		}
		for (int i = numbers.length; i < ar.length; i++) {
			assertNull(ar[i]);
		}
	}

	@Test
	void testClear() {
		collection.clear();
		assertFalse(collection.iterator().hasNext());
		assertEquals(0, collection.size());
	}

	@Test
	void removeIteratorTest() {
		final Iterator<Integer> it = collection.iterator();
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
		Integer num = it.next();
		assertTrue(collection.contains(num));
		it.remove();
		assertFalse(collection.contains(num));

		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
		Iterator<Integer> it1 = collection.iterator();

		while (it1.hasNext()) {
			num = it1.next();
		}
		assertTrue(collection.contains(num));
		it1.remove();
		assertFalse(collection.contains(num));

	}
}