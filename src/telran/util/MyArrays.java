package telran.util;

import java.util.Comparator;

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

}