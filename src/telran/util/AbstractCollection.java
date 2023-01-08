package telran.util;

import java.util.Iterator;

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
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {

		return size == 0;
	}

	@Override
	public void clear() {
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
	}
}