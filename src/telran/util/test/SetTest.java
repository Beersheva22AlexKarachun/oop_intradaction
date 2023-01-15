package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

public abstract class SetTest extends CollectionTest {
	Set<Integer> set;

	@BeforeEach
	void setUp() throws Exception {
		super.setUp();
		set = (Set<Integer>) collection;
	}

	@Override
	@Test
	void testAdd() {
		assertTrue(set.add(Integer.MAX_VALUE));
		assertFalse(set.add(Integer.MAX_VALUE));

	}

	@Override
	@Test
	void testRemove() {
		Integer[] expected = { -5, 10, 15, 100, 120, 280 };
		assertTrue(collection.remove((Integer) 134));

		Integer[] collectionArray = collection.toArray(empty);
		Arrays.sort(collectionArray);

		assertArrayEquals(expected, collectionArray);
		assertFalse(collection.remove((Integer) 134));
	}

	@Override
//	@Test
	void testRemoveIf() {
		Integer[] expected = { -5, 15 };
		assertTrue(collection.removeIf(n -> n % 2 == 0));

		Integer[] collectionArray = collection.toArray(empty);
		Arrays.sort(collectionArray);

		assertArrayEquals(expected, collectionArray);
		assertFalse(collection.removeIf(n -> n % 2 == 0));
		assertTrue(collection.removeIf(n -> true));
		assertTrue(collection.isEmpty());

	}

	@Test
	@Override
	void testIterator() {
		Integer[] actual = new Integer[numbers.length];
		int index = 0;
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			actual[index++] = it.next();
		}
		assertArrayEquals(set.toArray(empty), actual);
		assertThrowsExactly(NoSuchElementException.class, () -> it.next());

	}

	@Test
	@Override
	void testToArray() {
		Arrays.fill(ar, 10);
		assertTrue(ar == collection.toArray(ar));
		Arrays.sort(ar, 0, collection.size());
		Arrays.sort(numbers);
		for (int i = 0; i < numbers.length; i++) {
			assertEquals(ar[i], numbers[i]);
		}
		for (int i = numbers.length; i < ar.length; i++) {
			assertNull(ar[i]);
		}
	}

}