package telran.util.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

class ArrayListTest extends ListTest{
	@BeforeEach
	@Override 
	void setUp() throws Exception {
		collection = new ArrayList<>(2);
		super.setUp();
	}
	
	@Test
	void removeIfTest() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 500_000; i++) {
			list.add(20000);
			list.add(20001);
		}
		System.out.println(list.size());
		list.removeIf(x -> x%2==0);
		System.out.println(list.size());
	}
	
}