package telran.util.comparator;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		int res = 0;
		if (isEven(o1)) {
			res = isEven(o2) ? o1 - o2 : -1;
		} else {
			res = isEven(o2) ? 1 : o2 - o1;
		}
		return res;
	}

	private boolean isEven(int number) {
		return number % 2 == 0;
	}

}