package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import telran.util.absClasses.AbstractCollection;
import telran.util.interfaces.Collection;
import telran.util.interfaces.Map;
import telran.util.interfaces.Set;

public class LinkedHashSet<T> extends AbstractCollection<T> implements Set<T> {
	private Map<T, Node<T>> map;
	private T head;
	private T tail;

	private static class Node<T> {
		T prev;
		T next;

		Node(T next, T prev) {
			this.next = next;
			this.prev = prev;
		}
	}

	private class LinkedHashSetIterator implements Iterator<T> {
		private T current = head;
		private boolean flNext;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			T res = current;
			current = map.get(current).next;
			flNext = true;
			return res;
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			T removed = current == null ? tail : map.get(current).prev;
			LinkedHashSet.this.remove(removed);
			flNext = false;
		}
	}

	public LinkedHashSet() {
		map = new HashMap<>();
	}

	public LinkedHashSet(Collection<T> collection) {
		this();
		for (T item : collection) {
			add(item);
		}
	}

	@Override
	public int size() {
		return map.size();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean add(T element) {
		if (map.containsKey(element)) {
			return false;
		}
		if (isEmpty()) {
			head = tail = element;
			map.put(element, new Node<T>(null, null));
		} else {
			map.put(element, new Node<T>(null, tail));
			map.get(tail).next = element;
			tail = element;
		}
		return true;
	}

	@Override
	public boolean remove(T pattern) {
		Node<T> removed = map.remove(pattern);
		if (removed != null) {
			T prev = removed.prev;
			T next = removed.next;

			if (prev == null) {
				head = next;
			} else {
				map.get(prev).next = next;
			}

			if (next == null) {
				tail = prev;
			} else {
				map.get(next).prev = prev;
			}

		}
		return removed != null;
	}

	@Override
	public boolean contains(T pattern) {
		return map.containsKey(pattern);
	}

	@Override
	public Iterator<T> iterator() {
		return new LinkedHashSetIterator();
	}

	@Override
	public T get(T pattern) {
		return null;
	}

}