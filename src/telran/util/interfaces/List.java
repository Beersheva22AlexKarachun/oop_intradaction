package telran.util.interfaces;

public interface List<T> extends Collection<T> {
	void add(int index, T element);

	T remove(int index);

	@Override
	default boolean remove(T pattern) {
		int index = indexOf(pattern);
		if (index > -1) {
			remove(index);
		}
		return index > -1;
	}

	int indexOf(T pattern);

	int lastIndexOf(T pattern);

	T get(int index);

	void set(int index, T element);

	default void checkIndex(int index, int min, int max) {
		if (index > max || index < min)
			throw new IndexOutOfBoundsException(String.format("Index %1$s out of bounds for size %2$s", index, size()));
	}

	@Override
	default boolean contains(T pattern) {
		return indexOf(pattern) > -1;
	}
}