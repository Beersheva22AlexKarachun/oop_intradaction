package telran.util.absClasses;

import java.util.Iterator;
import java.util.Objects;

import telran.util.interfaces.Collection;

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
	public int hashCode() {
		return Objects.hash(size);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractCollection other = (AbstractCollection) obj;
		boolean res = size() == other.size();
		if (res) {
			Iterator<T> it1 = this.iterator();
			Iterator<T> it2 = other.iterator();
			while (it1.hasNext() && res) {
				res = it1.next().equals(it2.next());
			}
		}
		return res;
	}
}