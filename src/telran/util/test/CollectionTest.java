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
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;
import telran.util.interfaces.Collection;

public abstract class CollectionTest {
	protected Random random = new Random();
	protected static final int N_RUNS = 100;
	protected static final int N_NUMBERS = 1_000;
	private static final int MIN = Integer.MIN_VALUE;
	private static final int MAX = Integer.MAX_VALUE;

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
		assertTrue(collection.equals(copiedCollection));
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
	final void testIsEmpty() {
		assertFalse(collection.isEmpty());
		collection.removeIf(n -> true);
		assertTrue(collection.isEmpty());
	}

	@Test
	final void testSize() {
		assertEquals(numbers.length, collection.size());
	}

	@Test
	final void testContains() {
		assertTrue(collection.contains(numbers[0]));
		assertFalse(collection.contains(Integer.MIN_VALUE));
		for (Integer number : numbers) {
			assertTrue(collection.contains(number));
		}
	}

	@Test
	void testToArray() {
		Arrays.fill(ar, 10);
		assertTrue(ar == collection.toArray(ar));
		Arrays.sort(ar, 0, collection.size());
		Integer expected[] = Arrays.copyOf(numbers, numbers.length);
		Arrays.sort(expected);
		for (int i = 0; i < expected.length; i++) {
			assertEquals(ar[i], expected[i]);
		}
		for (int i = expected.length; i < ar.length; i++) {
			assertNull(ar[i]);
		}
	}

	@Test
	void testClear() {
		assertFalse(collection.isEmpty());
		collection.clear();
		assertFalse(collection.iterator().hasNext());
		assertTrue(collection.isEmpty());
	}

	@Test
	void testRemoveIterator() {
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

	protected Integer[] getRandomArray() {
		Integer result[] = new Integer[N_NUMBERS];
		for (int i = 0; i < N_NUMBERS; i++) {
			result[i] = random.nextInt(MIN, MAX);
		}
		return result;
	}

	protected void fillCollection(Collection<Integer> set, Integer[] numbers) {
		for (Integer num : numbers) {
			set.add(num);
		}
	}
}