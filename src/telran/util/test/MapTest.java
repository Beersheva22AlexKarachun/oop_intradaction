package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.HashSet;
import telran.util.LinkedList;
import telran.util.interfaces.Collection;
import telran.util.interfaces.Map;
import telran.util.interfaces.Map.Entry;
import telran.util.interfaces.Set;

abstract class MapTest {
	Map<Integer, String> map;

	@BeforeEach
	void setUp() throws Exception {
		map.put(1, "One");
		map.put(2, "Two");
		map.put(3, "Three");
	}

	@Test
	void getTest() {
		assertEquals("One", map.get(1));
		assertNull(map.get(1000));
	}

	@Test
	void getOrDefaultTest() {
		assertEquals("One", map.getOrDefault(1, "OneOne"));
		assertEquals("defaultValue", map.getOrDefault(4, "defaultValue"));
	}

	@Test
	void putTest() {
		assertEquals("One", map.put(1, "Один"));
		assertEquals("Один", map.get(1));
		assertNull(map.put(4, "Four"));
		assertEquals("Four", map.get(4));
	}

	@Test
	void putIfAbscentTest() {
		assertEquals("One", map.putIfAbsent(1, "OneOne"));
		assertEquals("One", map.get(1));
		assertNull(map.putIfAbsent(4, "Four"));
		assertEquals("Four", map.get(4));
	}

	@Test
	void containsKey() {
		assertTrue(map.containsKey(1));
		assertTrue(map.containsKey(2));
		assertFalse(map.containsKey(4));
	}

	@Test
	void containsValue() {
		assertTrue(map.containsValue("One"));
		assertTrue(map.containsValue("Two"));
		assertFalse(map.containsValue("Four"));
	}

	@Test
	void removeTest() {
		assertEquals("One", map.remove(1));
		assertNull(map.remove(1));
		assertNull(map.get(1));
	}

	@Test
	void valuesTest() {
		Collection<String> expectedList = new LinkedList<>();
		expectedList.add("One");
		expectedList.add("Two");
		expectedList.add("Three");
		assertEquals(expectedList, map.values());
	}

	@SuppressWarnings("unchecked")
	@Test
	void keySetTest() {
		try {
			Set<Integer> set = map.keySet();
			Set<Integer> expectedSet = set.getClass().getConstructor().newInstance();
			expectedSet.add(1);
			expectedSet.add(2);
			expectedSet.add(3);
			assertEquals(set.size(), expectedSet.size());
			assertEquals(set, expectedSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	void entrySetTest() {
		try {
			Set<Entry<Integer, String>> set = map.entrySet();
			Set<Entry<Integer, String>> expectedSet = set.getClass().getConstructor().newInstance();
			expectedSet.add(new Entry<>(1, "One"));
			expectedSet.add(new Entry<>(2, "Two"));
			expectedSet.add(new Entry<>(3, "Three"));
			assertEquals(set, expectedSet);
			set.forEach(entry -> {
				Entry<Integer, String> expEntry = expectedSet.get(entry);
				assertEquals(expEntry.getValue(), entry.getValue());
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	void equalsTest() {
		try {
			Map<Integer, String> newMap = map.getClass().getConstructor().newInstance(map);
			assertEquals(newMap, map);
		} catch (Exception e) {
			;
		}
	}

}