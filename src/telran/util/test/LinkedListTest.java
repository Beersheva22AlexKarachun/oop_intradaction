package telran.util.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

class LinkedListTest extends ListTest {
	LinkedList<Integer> linkedList;

	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new LinkedList<>();
		super.setUp();
		linkedList = (LinkedList<Integer>) list;

	}

	@Test
	void testLoop() {
		LinkedList<Integer> list1 = new LinkedList<>(collection);
		assertFalse(list1.hasLoop());
		list1.setNext(6, 0);
		assertTrue(list1.hasLoop());
	}

	@Test
	void isLoopTestEven() {
		list.add(300);
		assertFalse(linkedList.hasLoop());
		linkedList.setNext(list.size() - 1, 0);
		assertTrue(linkedList.hasLoop());

	}

	@Test
	void isLoopTestOdd() {
		assertFalse(linkedList.hasLoop());
		linkedList.setNext(list.size() - 1, 0);
		assertTrue(linkedList.hasLoop());

	}

	@Test
	void isLoopNoOneNode() {
		LinkedList<Integer> linkedList = new LinkedList<>();
		assertFalse(linkedList.hasLoop());
		linkedList.add(10);
		assertFalse(linkedList.hasLoop());
		linkedList.setNext(0, 0);
		assertTrue(linkedList.hasLoop());
	}
}