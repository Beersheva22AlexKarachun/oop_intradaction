package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import telran.util.MyArrays;
import telran.util.comparator.ArrayLengthComparator;

class Test {

	@org.junit.jupiter.api.Test
	void test() {
		MyArrays.sort(new Integer[4], new ArrayLengthComparator<Integer>());
	}

}
