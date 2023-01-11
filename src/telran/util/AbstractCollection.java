package telran.util;

public abstract class AbstractCollection<T> implements Collection<T> {
	protected int size;

	protected AbstractCollection() {
	};

	protected AbstractCollection(Collection<T> collection) {
		for (T item : collection) {
			add(item);
		}
	}

	protected boolean isEqual(T element, T pattern) {
		return element == null ? element == pattern : element.equals(pattern);
	}

	@Override
	final public int size() {
		return size;
	}

	@Override
	final public boolean isEmpty() {

		return size == 0;
	}
}