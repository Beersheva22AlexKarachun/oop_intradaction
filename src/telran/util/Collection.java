package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.*;

public interface Collection<T> extends Iterable<T> {
	boolean add(T element);

	boolean remove(T pattern);

	default boolean removeIf(Predicate<T> predicate) {
		Iterator<T> it = iterator();
		int oldSize = size();
		while (it.hasNext()) {
			if (predicate.test(it.next())) {
				it.remove();
			}
		}
		return oldSize > size();
	}

	boolean isEmpty();

	int size();

	boolean contains(T pattern);

	/*******************************/
	/**
	 * 
	 * @param ar
	 * @return array containing elements of a Collection if ar refers to memory that
	 *         is enough for all elements of Collection then new array is not
	 *         created, otherwise there will be created new array. if ar refers to
	 *         memory that is greater than required for all elements of Collection
	 *         then all elements of the Collection will be put in the array and rest
	 *         of memory will be filled by null's
	 */

	public default T[] toArray(T[] ar) {
		int size = size();
		if (ar.length < size) {
			ar = Arrays.copyOf(ar, size);
		}
		Iterator<T> it = iterator();
		for (int i = 0; i < size; i++) {
			ar[i] = it.next();
		}
		Arrays.fill(ar, size, ar.length, null);
		return ar;
	}

	public default void clear() {
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
	}
}