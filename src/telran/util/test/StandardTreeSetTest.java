package telran.util.test;

import org.junit.jupiter.api.BeforeEach;

import telran.util.StandardTreeSet;

public class StandardTreeSetTest extends SortedTest {
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new StandardTreeSet<Integer>();
		super.setUp();
	}

	@Override
	void testConstructorCopy() throws Exception {
		// TODO Auto-generated method stub
		super.testConstructorCopy();
	}
}