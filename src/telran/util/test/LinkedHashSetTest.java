package telran.util.test;

import org.junit.jupiter.api.BeforeEach;

import telran.util.LinkedHashSet;

public class LinkedHashSetTest extends SetTest {
	@BeforeEach
	@Override
	void setUp() throws Exception {
		collection = new LinkedHashSet<>();
		super.setUp();
	}
}