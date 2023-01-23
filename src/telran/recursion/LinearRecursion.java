package telran.recursion;

public class LinearRecursion {
	static public long factorial(int n) {
		long res = 0;
		if (n < 0) {
			res = factorial(-n);
		} else if (n < 2) {
			res = 1;
		} else {
			res = n * factorial(n - 1);
		}
		return res;
	}

	static public int power(int number, int power) {
		int res = 1;
		if (power < 0) {
			throw new IllegalArgumentException();
		}
		if (power > 1) {
			res = multiply(power(number, power - 1), number);
		} else if (power == 1) {
			res = number;
		}
		return res;
	}

	private static int multiply(int number, int multiplier) {
		int res = 0;
		if (multiplier < 0) {
			res = -multiply(number, -multiplier);
		} else if (multiplier > 0) {
			res = number + multiply(number, multiplier - 1);
		}
		return res;
	}

	static public long sum(int ar[]) {
		return sum(0, ar);
	}

	private static long sum(int firstIndex, int[] ar) {
		long res = 0;
		if (firstIndex < ar.length) {
			res = ar[firstIndex] + sum(firstIndex + 1, ar);
		}
		return res;
	}

	public static long square(int x) {
		x = x < 0 ? -x : x;
		return x == 0 ? 0 : (x + x - 1 + square(--x));
	}

	public static void reverse(int[] ar) {
		reverse(0, ar.length - 1, ar);
	}

	private static void reverse(int firstIndex, int lastIndex, int[] ar) {
		if (firstIndex < lastIndex) {
			swap(ar, firstIndex, lastIndex);
			reverse(firstIndex + 1, lastIndex - 1, ar);
		}
	}

	private static void swap(int[] ar, int firstIndex, int lastIndex) {
		int tmp = ar[firstIndex];
		ar[firstIndex] = ar[lastIndex];
		ar[lastIndex] = tmp;
	}

	public static boolean isSubstring(String string, String pattern) {
		return isSubstring(string, pattern, 0);
	}

	private static boolean isSubstring(String str, String pattern, int i) {
		if (i == pattern.length()) {
			return true;
		} else if (str.length() == 0) {
			return false;
		} else if (str.charAt(0) == pattern.charAt(i)) {
			return isSubstring(str.substring(1), pattern, ++i);
		} else {
			return isSubstring(str.substring(1), pattern, 0);
		}
	}
}