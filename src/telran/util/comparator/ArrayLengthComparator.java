package telran.util.comparator;

import java.util.Comparator;

public class ArrayLengthComparator<T> implements Comparator<T[]> {

	@Override
	public int compare(T[] o1, T[] o2) {
		return o1.length - o2.length;
	}

}