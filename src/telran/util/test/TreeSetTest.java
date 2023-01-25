package telran.util.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

public class TreeSetTest extends SortedTest {
	TreeSet<Integer> tree;

	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new TreeSet<>();
		super.setUp();
		tree = (TreeSet<Integer>) collection;
	}

	@Test
	@Override
	void testIterator() {
		super.testIterator();
		for (int i = 0; i < N_RUNS; i++) {
			TreeSet<Integer> treeSet = new TreeSet<>();
			HashSet<Integer> hashSet = new HashSet<>();
			Integer[] array = getRandomArray();
			fillCollection(hashSet, array);
			fillCollection(treeSet, array);
			Integer[] arrayFromHash = hashSet.toArray(empty);
			Arrays.sort(arrayFromHash);
			assertArrayEquals(treeSet.toArray(empty), arrayFromHash);
		}

	}

	@Override
	@Test
	void testRemove() {
		Integer[] expected = { -5, 10, 15, 100, 120, 280 };
		assertTrue(collection.remove((Integer) 134));

		Integer[] collectionArray = collection.toArray(empty);

		assertArrayEquals(expected, collectionArray);
		assertFalse(collection.remove((Integer) 134));
	}

	@Override
	@Test
	void testRemoveIterator() {
		super.testRemoveIterator();
		for (int i = 0; i < N_RUNS; i++) {
			TreeSet<Integer> treeSet = new TreeSet<>();
			HashSet<Integer> hashSet = new HashSet<>();
			Integer[] array = getRandomArray();
			fillCollection(hashSet, array);
			fillCollection(treeSet, array);
			Iterator<Integer> it = treeSet.iterator();
			for (int j = 0; j < treeSet.size() / 2; j++) {
				it.next();
			}

			hashSet.remove(it.next());
			it.remove();

			Integer[] arrayFromHash = hashSet.toArray(empty);
			Arrays.sort(arrayFromHash);
			assertTrue(treeSet.toArray(empty).length == arrayFromHash.length);
			assertArrayEquals(treeSet.toArray(empty), arrayFromHash);
		}
	}

	@Test
	void displayRotatatedTest() {
		tree.displayTreeRotated();
	}

	@Test
	void heightTreeTest() {
		assertEquals(4, tree.height());
		tree.clear();
		assertEquals(0, tree.height());
	}

	@Test
	void widthTreeTest() {
		assertEquals(4, tree.width());
		tree.clear();
		assertEquals(0, tree.width());
	}

	@Test
	void inversionTest() {
		// {10, 100, -5, 134, 280, 120, 15};
		tree.inversion();
		Integer[] expected = { 280, 134, 120, 100, 15, 10, -5 };
		Integer[] actual = new Integer[expected.length];
		int index = 0;
		for (Integer num : tree) {
			actual[index++] = num;
		}

		assertArrayEquals(expected, actual);
		assertArrayEquals(tree.toArray(actual), actual);

		tree.inversion();
		Arrays.sort(expected);
		index = 0;

		for (Integer num : tree) {
			actual[index++] = num;
		}
		assertArrayEquals(expected, actual);
		assertArrayEquals(tree.toArray(actual), actual);
	}
}