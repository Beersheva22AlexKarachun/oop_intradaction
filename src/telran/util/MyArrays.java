package telran.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class MyArrays {
	public static <T> void sort(T[] objects, Comparator<T> comparator) {
		int length = objects.length;
		do {
			length--;
		} while (moveMaxAtEnd(objects, length, comparator));

	}

	public static <T> int binarySearch(T[] array, T searchedItem, Comparator<T> comp) {
		int left = 0;
		int right = array.length - 1;
		int middle = right / 2;

		while (left <= right) {
			int compareRes = comp.compare(array[middle], searchedItem);
			if (compareRes > 0) {
				right = middle - 1;
			} else if (compareRes < 0) {
				left = middle + 1;
			} else {
				return middle;
			}
			middle = (right + left) / 2;
		}
		return -(left + 1);
	}

	private static <T> boolean moveMaxAtEnd(T[] objects, int length, Comparator<T> comp) {
		boolean res = false;
		for (int i = 0; i < length; i++) {
			if (comp.compare(objects[i], objects[i + 1]) > 0) {
				swap(objects, i, i + 1);
				res = true;
			}
		}
		return res;
	}

	private static <T> void swap(T[] objects, int i, int j) {
		T tmp = objects[i];
		objects[i] = objects[j];
		objects[j] = tmp;
	}

	public static <T> T[] filter(T[] array, Predicate<T> predicate) {
		int countPredicate = getCountPredicate(array, predicate);

		T[] res = Arrays.copyOf(array, countPredicate);
		int index = 0;
		for (T element : array) {
			if (predicate.test(element)) {
				res[index++] = element;
			}
		}

		return res;
	}

	private static <T> int getCountPredicate(T[] array, Predicate<T> predicate) {
		int res = 0;
		for (T element : array) {
			if (predicate.test(element)) {
				res++;
			}
		}
		return res;
	}

	public static <T> T[] removeIf(T[] array, Predicate<T> predicate) {
		return filter(array, predicate.negate());
	}

	public static <T> T[] removeRepeated(T[] array) {
		T[] res = Arrays.copyOf(array, array.length);
		Arrays.fill(res, null);
		int i = 0;
		while (array.length > 0) {
			res[i++] = array[0];
			array = removeIf(array, x -> contains(res, x));
		}
		return Arrays.copyOf(res, i);
	}

	public static <T> boolean contains(T[] array, T pattern) {
		boolean res = false;
		int i = 0;
		while (i < array.length && !res) {
			res = array[i] != null ? array[i].equals(pattern) : pattern == null;
			i++;
		}
		return res;
	}

}
