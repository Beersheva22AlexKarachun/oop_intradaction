package telran.util.test;



import org.junit.jupiter.api.BeforeEach;

import telran.util.*;

class ArrayListTest extends ListTest{
	@BeforeEach
	@Override 
	void setUp() throws Exception {
		collection = new ArrayList<>(2);
		super.setUp();
	}
	
}