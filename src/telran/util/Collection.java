package telran.util;

import java.util.function.*;

public interface Collection<T> extends Iterable<T> {
	boolean add(T element);

	boolean remove(T pattern);

	boolean removeIf(Predicate<T> predicate);

	default boolean isEmpty() {
		return size() == 0;
	};

	int size();

	boolean contains(T pattern);

	T[] toArray();

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
	T[] toArray(T[] ar);

	void clear();

	default boolean isEqual(T item1, T item2) {
		return item1 == null ? item1 == item2 : item1.equals(item2);
	}
}