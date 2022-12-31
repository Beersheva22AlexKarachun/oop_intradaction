package telran.util;

public interface List<T> extends Collection<T>, Iterable<T> {
	void add(int index, T element);

	T remove(int index);

	int indexOf(T pattern);

	int lastIndexOf(T pattern);

	T get(int index);

	void set(int index, T element);
}