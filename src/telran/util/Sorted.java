package telran.util;

public interface Sorted<T> extends Set<T> {
	T floor(T element); // returns greatest among elements less than element or equal

	T ceiling(T element); // returns least among elements greater than element or equal

	T first();

	T last();
}