package telran.util.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

class LinkedListTest extends ListTest {
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new LinkedList<>();
		super.setUp();
	}

	@Test
	void testLoop() {
		LinkedList<Integer> list1 = new LinkedList<>(collection);
		assertFalse(list1.hasLoop());
		list1.setNext(6, 0);
		assertTrue(list1.hasLoop());
	}
}